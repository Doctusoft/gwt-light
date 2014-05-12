package com.doctusoft.gwt.light;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.google.gwt.event.dom.client.ClickHandler;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public abstract class AbstractLightClickable<Actual extends AbstractLightClickable<Actual>> extends AbstractLightLabel<Actual> {

	public AbstractLightClickable(final JQuery selector) {
		super(selector);
	}

	public AbstractLightClickable(final String selector) {
		super(selector);
	}
	
	/**
	 * @deprecated use {@link EmptyEventListener} instead 
	 */
	@Deprecated
	public Actual click(final ClickHandler handler) {
		click(new EmptyEventListener() {
			
			@Override
			public void handle() {
				handler.onClick(null);;
			}
		});
		return (Actual) this;
	}

	public Actual click(final EmptyEventListener listener) {
		root.click(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (!isDisabled()) {
					listener.handle();
				}
			}
		});
		return (Actual) this;
	}
	
	@SuppressWarnings("unchecked")
	public void setVisibilityBinding(final ValueBinding<Boolean> binding) {
		setVisible(binding.getValue());
		if (binding instanceof ObservableValueBinding<?>) {
			
			((ObservableValueBinding<Boolean>) binding).addValueChangeListener(new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean value) {
					setVisible(value);
				}
			});
		}
	}
	
}
