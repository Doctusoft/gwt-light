package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.ViewOf;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class ListActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;

	public ListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<ListActivity> listView = clientFactory.getListView();
		listView.setPresenter(this);
		panel.setWidget(listView);
	}

}
