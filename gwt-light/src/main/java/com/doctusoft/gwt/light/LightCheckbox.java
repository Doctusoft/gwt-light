package com.doctusoft.gwt.light;

import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class LightCheckbox extends AbstractLightWidget<LightCheckbox> {
	
	@ObservableAttribute
	private boolean checked;
	
	/**
	 * https://code.google.com/p/gwt-jquery/issues/detail?id=5
	 */
	public static native void propBoolean(JQuery root, String propertyName, boolean value) /*-{
		root.prop(propertyName, value);
	}-*/;

	public LightCheckbox(JQuery selector) {
		super(selector);
		_checked.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Boolean.TRUE == newValue) {
					propBoolean(root, "checked", true);
				} else {
					propBoolean(root, "checked", false);
				}
			}
		});
		root.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				setChecked(root.is(":checked"));
			}
		});
	}
	
	public LightCheckbox(String selector) {
		this(JQuery.select(selector));
	}
	
	public LightCheckbox bind(final ValueBinding<Boolean> binding) {
		Bindings.bind(binding, Bindings.obs(this).get(_checked));
		return this;
	}

}
