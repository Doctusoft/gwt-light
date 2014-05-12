package com.doctusoft.gwt.light;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.xedge.jquery.client.JQuery;

public abstract class AbstractLightLabel<Actual extends AbstractLightLabel<Actual>> extends AbstractLightWidget<Actual> {
	
	@ObservableProperty
	private String text;
	
	public AbstractLightLabel(JQuery selector) {
		super(selector);
		AbstractLightLabel_._text.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.html(newValue);
			}
		});
	}
	
	public AbstractLightLabel(String selector) {
		this(JQuery.select(selector));
	}

	public Actual text(String text) {
		setText(text);
		return (Actual) this;
	}
	
	public Actual bindText(final ValueBinding<String> binding) {
		Bindings.bind(binding, Bindings.on(this).get((com.doctusoft.bean.ObservableProperty)AbstractLightLabel_._text));
		return (Actual) this;
	}

}
