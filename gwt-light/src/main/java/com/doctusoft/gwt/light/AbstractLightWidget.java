package com.doctusoft.gwt.light;

import static com.google.common.base.Preconditions.checkNotNull;
import lombok.Getter;
import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.doctusoft.common.core.bean.binding.observable.ObservableValueBinding;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.xedge.jquery.client.JQuery;

/**
 * TODO consider naming, LightDiv, LightPanel, LightContainer, etc. 
 */
public abstract class AbstractLightWidget<Actual extends AbstractLightWidget<Actual>> implements IsWidget {
	
	@Getter
	protected JQuery root;
	
	@ObservableAttribute(staticField=false)
	private boolean visible;
	
	@ObservableAttribute(staticField=false)
	private boolean disabled;

	public AbstractLightWidget(JQuery root) {
		this.root = root;
		init();
	}
	
	public AbstractLightWidget(String selector) {
		this.root = JQuery.select(selector);
		init();
	}

	protected void init() {
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
		_disabled.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == Boolean.TRUE) {
					root.attr("disabled", "disabled");
				} else {
					root.removeAttr("disabled");
				}
			}
		});
	}
	
	/**
	 * Formerly all Light* components extended {@link Widget}, but I've found that it's not at all
	 * necessary, because we only ocassionally need features of that. No we convert things to GWT
	 * widgets only when actually needed, so we keep things more light-weight.
	 */
	@Override
	public Widget asWidget() {
		Element element = root.get(0);
		checkNotNull(element);
		return new LightWidgetWrapper(element);
	}
	
	private class LightWidgetWrapper extends Widget {
		public LightWidgetWrapper(Element element) {
			setElement(element);
		}
	}
	
	public Actual bindVisible(ValueBinding<Boolean> visibleBinding) {
		Bindings.bind(visibleBinding, Bindings.on(this).get((com.doctusoft.common.core.bean.ObservableAttribute)_visible));
		return (Actual) this;
	}

	public Actual bindDisabled(ValueBinding<Boolean> disabledBinding) {
		Bindings.bind(disabledBinding, Bindings.on(this).get((com.doctusoft.common.core.bean.ObservableAttribute)_disabled));
		return (Actual) this;
	}
	
	/**
	 * Adds or removes a specific styleclass on the given element according to the classPresence binding value 
	 */
	public Actual bindStyleClassToggle(String styleClass, ValueBinding<Boolean> classPresence) {
		new StyleClassToggleBinder(styleClass, classPresence);
		return (Actual) this;
	}
	
	private class StyleClassToggleBinder {
		private String styleClass;

		public StyleClassToggleBinder(String styleClass, ValueBinding<Boolean> classPresence) {
			this.styleClass = styleClass;
			applyValue(classPresence.getValue());
			if (classPresence instanceof ObservableValueBinding<?>) {
				((ObservableValueBinding<Boolean>) classPresence).addValueChangeListener(new ValueChangeListener<Boolean>() {
					@Override
					public void valueChanged(Boolean newValue) {
						applyValue(newValue);;
					}
				});
			}
		}
		public void applyValue(Boolean value) {
			if (value == Boolean.TRUE) {
				root.addClass(styleClass);
			} else {
				root.removeClass(styleClass);
			}
		}
	}
}