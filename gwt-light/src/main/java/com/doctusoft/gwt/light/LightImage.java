package com.doctusoft.gwt.light;

import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.xedge.jquery.client.JQuery;

public class LightImage extends AbstractLightWidget<LightImage> {
	
	@ObservableAttribute
	private String src;
	
	@ObservableAttribute
	private String alt;

	public LightImage(final JQuery selector) {
		super(selector);
		initImage();
	}

	public LightImage(final String selector) {
		this(JQuery.select(selector));
	}
	
	protected void initImage() {
		_visible.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == Boolean.TRUE) {
					root.show();
				} else {
					root.hide();
				}
			}
		});
		_src.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.attr("src", newValue);
			}
		});
		_alt.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.attr("alt", newValue);
			}
		});
	}
	
	public void setAlt(final String alt){
		root.attr("alt", alt);
	}
	
	public LightImage bindSrc(final ValueBinding<String> srcBinding) {
		Bindings.bind(srcBinding, Bindings.on(this).get(_src));
		return this;
	}
	
	public LightImage bindAlt(final ValueBinding<String> altBinding) {
		Bindings.bind(altBinding, Bindings.on(this).get(_alt));
		return this;
	}
}
