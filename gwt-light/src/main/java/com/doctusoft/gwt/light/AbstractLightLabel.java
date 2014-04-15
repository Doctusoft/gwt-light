package com.doctusoft.gwt.light;

import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.doctusoft.common.core.bean.binding.observable.ObservableValueBinding;
import com.xedge.jquery.client.JQuery;

public abstract class AbstractLightLabel<Actual extends AbstractLightLabel<Actual>> extends AbstractLightWidget<Actual> {
	
	@ObservableAttribute(staticField=false)
	private String text;
	
	public AbstractLightLabel(JQuery selector) {
		super(selector);
		_text.addChangeListener(this, new ValueChangeListener<String>() {
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
		Bindings.bind(binding, Bindings.on(this).get((com.doctusoft.common.core.bean.ObservableAttribute)_text));
		return (Actual) this;
	}

}
