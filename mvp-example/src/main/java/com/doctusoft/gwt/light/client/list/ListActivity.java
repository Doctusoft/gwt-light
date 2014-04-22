package com.doctusoft.gwt.light.client.list;

import lombok.MethodRef;
import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.binding.observable.ObservableList;
import com.doctusoft.gwt.light.ViewOf;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ListActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	@ObservableAttribute
	private ObservableList<TestEntityDTO> testEntities;
	
	private static int idSeries = 1;

	public ListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
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
	}
}
