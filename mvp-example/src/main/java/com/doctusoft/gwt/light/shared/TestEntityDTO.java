package com.doctusoft.gwt.light.shared;

import lombok.ObservableAttribute;
import lombok.ToString;

@ToString
public class TestEntityDTO {
	
	@ObservableAttribute
	private long id;
	
	@ObservableAttribute
	private String name;

}
