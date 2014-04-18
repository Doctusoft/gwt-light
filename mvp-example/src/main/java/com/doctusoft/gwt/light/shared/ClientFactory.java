package com.doctusoft.gwt.light.shared;

import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {

	public EventBus getEventBus();
	
	public IPlaceController getPlaceController();
	
	public ViewOf<IndexActivity> getIndexView();
	
	public ViewOf<ListActivity> getListView();
}
