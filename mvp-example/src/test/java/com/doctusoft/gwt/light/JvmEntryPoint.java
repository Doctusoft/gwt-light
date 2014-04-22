package com.doctusoft.gwt.light;

import lombok.Getter;

import com.doctusoft.gwt.light.mvp.IPlaceController;
import com.doctusoft.gwt.light.shared.ClientFactory;


public class JvmEntryPoint {
	
	@Getter
	private ClientFactory clientFactory;
	
	@Getter
	private IPlaceController placeController;

	public JvmEntryPoint() {
		clientFactory = new MockClientFactoryImpl();
		placeController = clientFactory.getPlaceController();
	}
}
