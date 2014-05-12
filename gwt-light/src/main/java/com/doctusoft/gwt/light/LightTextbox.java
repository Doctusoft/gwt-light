package com.doctusoft.gwt.light;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.google.gwt.user.client.Timer;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class LightTextbox extends AbstractLightWidget<LightTextbox> {
	
	private boolean immediate = false;
	
	@ObservableProperty
	private String text;
	
	public LightTextbox(JQuery selector) {
		super(selector);
		initTextbox();
	}
	
	public LightTextbox(String selector) {
		this(JQuery.select(selector));
	}
	
	protected void initTextbox() {
		LightTextbox_._text.addChangeListener(this, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				root.val(newValue);
			}
		});
		root.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				setText(root.val());
			}
		});
		// TODO using 'keyup' might save us from using that ugly Timer
		root.keypress(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				// the event is invoked BEFORE the pressed key is actually appended to the value (or backspace and other keys change the value)
				if (immediate) {
					// so this timer ensures that when we invoke getText(), the value is already correct
					new Timer() {
						@Override
						public void run() {
							setText(root.val());
						}
					}.schedule(50);
				}
			}
		});
	}

	public LightTextbox setFocus() {
		root.focus();
		return this;
	}
	
	public LightTextbox bind(final ValueBinding<String> binding) {
		Bindings.bind(binding, Bindings.obs(this).get(LightTextbox_._text));
		return this;
	}
	
	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}
	
	public boolean isImmediate() {
		return immediate;
	}

	/**
	 * If set, the element with the given id will be clicked automatically when the user presses enter in the textbox.
	 * This can make things work if the textbox was in an actual form. 
	 */
	public LightTextbox setDefaultCommandButton(final String id) {
		root.keyup(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				 if (event.getKeyCode() == 13) {
					 JQuery.select("#" + id).focus().click(); // it'S important to trigger the binding of the textbox by taking away itd focus
				 }
			}
		});
		return this;
	}
	
}
