package com.doctusoft.gwt.light;

import lombok.Getter;
import lombok.LazyGetter;

import com.doctusoft.gwt.light.client.ExampleActivityMapper;
import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialogPresenter;
import com.doctusoft.gwt.light.client.list.TestRemoteServiceAsync;
import com.doctusoft.gwt.light.server.TestServiceRPC;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.IPlaceController;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class MockClientFactoryImpl implements ClientFactory {

	@Getter
	private EventBus eventBus = new SimpleEventBus();
	
	@Getter
	private IPlaceController placeController;
	
	@Getter
	private Activity currentPresenter;
	
	public MockClientFactoryImpl() {
		final ExampleActivityMapper activityMapper = new ExampleActivityMapper(this);
		final IntegrationActivityManager activityManager = new IntegrationActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(new AcceptsOneWidget() {
			@Override
			public void setWidget(IsWidget w) {
			}
		});
		placeController = new IPlaceController() {
			@Override
			public void goTo(Place newPlace) {
				activityManager.onPlaceChange(new PlaceChangeEvent(newPlace));
				currentPresenter = activityManager.getCurrentActivity();
			}
		};
	}
	
	@LazyGetter
	private ViewOf<IndexActivity> indexView = new TotallyDummyEmptyView<>();

	@LazyGetter
	private ViewOf<ListActivity> listView = new TotallyDummyEmptyView<>();

	@LazyGetter
	private ViewOf<NewEntityModalDialogPresenter> newEntityModalDialog = new TotallyDummyEmptyView<>();

	/**
	 * Normally here you'd use a Guice injector or something to get reference to the RPC implementation
	 */
	@LazyGetter
	private TestRemoteServiceAsync testRemoteServiceAsync = IntegrationServiceFactory.getService(TestRemoteServiceAsync.class,
			new TestServiceRPC());


}
