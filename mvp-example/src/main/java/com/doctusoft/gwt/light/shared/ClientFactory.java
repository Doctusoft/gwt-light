package com.doctusoft.gwt.light.shared;

import com.doctusoft.gwt.light.ViewOf;
import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialogPresenter;
import com.doctusoft.gwt.light.client.list.TestRemoteServiceAsync;
import com.doctusoft.gwt.light.mvp.IPlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {

	public EventBus getEventBus();
	
	public IPlaceController getPlaceController();
	
	public ViewOf<IndexActivity> getIndexView();
	
	public ViewOf<ListActivity> getListView();
	
	public ViewOf<NewEntityModalDialogPresenter> getNewEntityModalDialog();
	
	public TestRemoteServiceAsync getTestRemoteServiceAsync();
}
