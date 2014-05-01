package com.doctusoft.gwt.light.client;

import lombok.Getter;
import lombok.LazyGetter;

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
	
	@LazyGetter
	private final ViewOf<IndexActivity> indexView = new IndexViewImpl();

	@LazyGetter
	private final ViewOf<ListActivity> listView = new ListViewImpl();
	
	@LazyGetter
	private final ViewOf<NewEntityModalDialogPresenter> newEntityModalDialog = new NewEntityModalDialog();
	
	@LazyGetter
	private final TestRemoteServiceAsync testRemoteServiceAsync = GWT.create(TestRemoteService.class);
}
