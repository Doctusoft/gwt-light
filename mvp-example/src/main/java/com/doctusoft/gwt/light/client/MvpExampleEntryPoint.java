package com.doctusoft.gwt.light.client;

import com.doctusoft.gwt.light.client.index.IndexPlace;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.GwtPlaceControllerWrapper;
import com.doctusoft.gwt.light.shared.IPlaceController;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class MvpExampleEntryPoint implements EntryPoint {
	
	private ClientFactory clientFactory;

	public void onModuleLoad() {
		clientFactory = GWT.create(ClientFactory.class);
		final IPlaceController placeController = clientFactory.getPlaceController();
		final ActivityMapper activityMapper = new ExampleActivityMapper(clientFactory);
		final ActivityManager activityManager = new ActivityManager(activityMapper, clientFactory.getEventBus());
		SimplePanel display = new SimplePanel();
		activityManager.setDisplay(display);
		RootPanel.get().add(display);
		
		final PlaceHistoryMapper historyMapper = GWT.create(ExamplePlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		
		historyHandler.register(((GwtPlaceControllerWrapper)placeController).getPlaceController(), clientFactory.getEventBus(), new IndexPlace());
		
		historyHandler.handleCurrentHistory();

	}

}
