package com.doctusoft.gwt.light.shared;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ObservableAttribute;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of={"id", "name"})
public class TestEntityDTO implements Serializable {
	
	@ObservableAttribute
	private long id;
	
	@ObservableAttribute
	private String name;

}
