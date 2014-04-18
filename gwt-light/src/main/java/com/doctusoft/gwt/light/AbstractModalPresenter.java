package com.doctusoft.gwt.light;

import lombok.ObservableAttribute;

public abstract class AbstractModalPresenter {
	
	@ObservableAttribute(staticField=false)
	private boolean visible;
	
	
}
