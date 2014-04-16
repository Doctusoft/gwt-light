package com.doctusoft.gwt.light.client;

import lombok.Getter;
import lombok.LazyGetter;

import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.index.IndexViewImpl;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.doctusoft.gwt.light.shared.GwtPlaceControllerWrapper;
import com.doctusoft.gwt.light.shared.IPlaceController;
import com.doctusoft.gwt.light.shared.ViewOf;
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
}
