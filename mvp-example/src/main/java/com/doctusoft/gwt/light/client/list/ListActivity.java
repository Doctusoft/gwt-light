package com.doctusoft.gwt.light.client.list;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ListActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	@ObservableProperty
	private ObservableList<TestEntityDTO> testEntities;
	
	private static int idSeries = 1;

	private TestRemoteServiceAsync testService;

	public ListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		testService = clientFactory.getTestRemoteServiceAsync();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		testEntities = new ObservableList<>();
		TestEntityDTO dto1 = new TestEntityDTO();
		dto1.setId(1);
		dto1.setName("helloworld");
		testEntities.add(dto1);
		ViewOf<ListActivity> listView = clientFactory.getListView();
		listView.setPresenter(this);
		panel.setWidget(listView);
	}

	@MethodRef
	public void addNewEntity() {
		// instead of using listeners or further bindings, we use this solution to handle actions from the inner presenter
		NewEntityModalDialogPresenter dialogPresenter = new NewEntityModalDialogPresenter() {
			@Override
			public void save() {
				super.save();
				TestEntityDTO dto = getDto();
				dto.setId(++ idSeries);
				testEntities.add(dto);
				testService.itemAdded(dto, new EmtpyCallback<Void>());
			}
		};
		dialogPresenter.setDto(new TestEntityDTO());
		NewEntityModalDialogPresenter.form.cancel();	// cancel any possible previous change
		clientFactory.getNewEntityModalDialog().setPresenter(dialogPresenter);
		dialogPresenter.setVisible(true);
	}
	
	@MethodRef
	public void editEntity(TestEntityDTO dto) {
		// nothing to override in this case, everything works fine by default
		NewEntityModalDialogPresenter dialogPresenter = new NewEntityModalDialogPresenter();
		dialogPresenter.setDto(dto);
		NewEntityModalDialogPresenter.form.cancel();	// cancel any possible previous change
		clientFactory.getNewEntityModalDialog().setPresenter(dialogPresenter);
		dialogPresenter.setVisible(true);
	}
	
	@MethodRef
	public void removeEntity(TestEntityDTO dto) {
		testEntities.remove(dto);
		testService.itemRemoved(dto, new EmtpyCallback<Void>());
	}
	
	public static class EmtpyCallback<T> implements AsyncCallback<T> {
		@Override
		public void onFailure(Throwable caught) {
		}
		@Override
		public void onSuccess(T result) {
		}
	}
}
