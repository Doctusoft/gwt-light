package com.doctusoft.gwt.light.client;

import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.index.IndexPlace;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ExampleActivityMapper implements ActivityMapper {
	
	private ClientFactory clientFactory;

	public ExampleActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof IndexPlace)
			return new IndexActivity(clientFactory);
		return null;
	}

}
