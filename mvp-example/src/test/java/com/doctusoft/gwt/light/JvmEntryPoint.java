package com.doctusoft.gwt.light;

import lombok.Getter;

import com.doctusoft.gwt.light.shared.IPlaceController;


public class JvmEntryPoint {
	
	@Getter
	private MockClientFactoryImpl clientFactory;
	
	@Getter
	private IPlaceController placeController;

	public JvmEntryPoint() {
		clientFactory = new MockClientFactoryImpl();
		placeController = clientFactory.getPlaceController();
	}
}
