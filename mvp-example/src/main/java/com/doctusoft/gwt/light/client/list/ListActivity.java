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
		NewEntityModalDialogPresenter dialogPresenter = new NewEntityModalDialogPresenter() {
			
		};
		clientFactory.getNewEntityModalDialog().setPresenter(dialogPresenter);
		dialogPresenter.setVisible(true);
	}
}
