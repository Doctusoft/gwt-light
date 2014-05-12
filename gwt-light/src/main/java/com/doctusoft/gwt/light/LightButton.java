package com.doctusoft.gwt.light;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.xedge.jquery.client.JQuery;

public class LightButton extends AbstractLightClickable<LightButton> {
	
	@ObservableProperty
	private boolean disabled;

	public LightButton(JQuery selector) {
		super(selector);
		LightButton_._disabled.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean disabled) {
				Element e = root.get(0);
				if (e instanceof ButtonElement) {
					ButtonElement ie = (ButtonElement) e;
					ie.setDisabled(disabled);
				}
				
				if (disabled) {
					root.addClass("btn-disable");
				} else {
					root.removeClass("btn-disable");
				}
			}
		});
	}
	
	public LightButton(String selector) {
		this(JQuery.select(selector));
	}
	
	public LightButton text(final String text) {
		setText(text);
		return this;
	}

}
