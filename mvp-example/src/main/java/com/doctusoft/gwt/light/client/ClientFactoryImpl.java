package com.doctusoft.gwt.light.client;

import lombok.Getter;

import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.index.IndexViewImpl;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.doctusoft.gwt.light.client.list.ListViewImpl;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialog;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialogPresenter;
import com.doctusoft.gwt.light.client.list.TestRemoteService;
import com.doctusoft.gwt.light.client.list.TestRemoteServiceAsync;
import com.doctusoft.gwt.light.mvp.GwtPlaceControllerWrapper;
import com.doctusoft.gwt.light.mvp.IPlaceController;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {

	@Getter
	private final EventBus eventBus = new SimpleEventBus();

	@Getter
	private final IPlaceController placeController = new GwtPlaceControllerWrapper(new PlaceController(eventBus));
	
	private ViewOf<IndexActivity> indexView = null;
	
	@Override
	public ViewOf<IndexActivity> getIndexView() {
		if (indexView == null) {
			indexView = new IndexViewImpl();;
		}
		return indexView;
	}

	private ViewOf<ListActivity> listView = null;
	
	@Override
	public ViewOf<ListActivity> getListView() {
		if (listView == null) {
			listView = new ListViewImpl();
		}
		return listView;
	}
	
	private ViewOf<NewEntityModalDialogPresenter> newEntityModalDialog = null;
	
	@Override
	public ViewOf<NewEntityModalDialogPresenter> getNewEntityModalDialog() {
		if (newEntityModalDialog == null) {
			newEntityModalDialog = new NewEntityModalDialog();
		}
		return newEntityModalDialog;
	}
	
	private TestRemoteServiceAsync testRemoteServiceAsync = null;
	
	@Override
	public TestRemoteServiceAsync getTestRemoteServiceAsync() {
		if (testRemoteServiceAsync == null) {
			testRemoteServiceAsync = GWT.create(TestRemoteService.class);;
		}
		return testRemoteServiceAsync;
	}
}
