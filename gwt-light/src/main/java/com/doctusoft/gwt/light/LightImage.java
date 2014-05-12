package com.doctusoft.gwt.light;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.xedge.jquery.client.JQuery;

public class LightImage extends AbstractLightWidget<LightImage> {
	
	@ObservableProperty
	private String src;
	
	@ObservableProperty
	private String alt;

	public LightImage(final JQuery selector) {
		super(selector);
		initImage();
	}

	public LightImage(final String selector) {
		this(JQuery.select(selector));
	}
	
	protected void initImage() {
		AbstractLightWidget_._visible.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == Boolean.TRUE) {
					root.show();
				} else {
					root.hide();
				}
			}
		});
		LightImage_._src.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.attr("src", newValue);
			}
		});
		LightImage_._alt.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.attr("alt", newValue);
			}
		});
	}
	
	public LightImage bindSrc(final ValueBinding<String> srcBinding) {
		Bindings.bind(srcBinding, Bindings.on(this).get(LightImage_._src));
		return this;
	}
	
	public LightImage bindAlt(final ValueBinding<String> altBinding) {
		Bindings.bind(altBinding, Bindings.on(this).get(LightImage_._alt));
		return this;
	}
}
