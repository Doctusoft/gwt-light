package com.doctusoft.gwt.light.shared;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import com.doctusoft.ObservableProperty;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of={"id", "name"})
public class TestEntityDTO implements Serializable {
	
	@ObservableProperty
	private long id;
	
	@ObservableProperty
	private String name;

}
