package com.doctusoft.gwt.light;

import java.util.List;

import com.doctusoft.common.core.bean.ListenerRegistration;
import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.doctusoft.common.core.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.common.core.bean.internal.AttributeListeners;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Forms provide shadow values for form input elements that are bound to model object attributes.
 * The values entered to the input elements are reflected by the shadow values, but they are
 * only propagated to their original sources if the form is committed. 
 */
public class ShadowForm {
	
	protected List<ShadowValueHolder> holders = Lists.newArrayList();
	
	public <T> ValueBinding<T> wrap(ValueBinding<T> binding) {
		return new WrappedFormBinding<T>(binding);
	}
	
	/**
	 * If the binding is observable and it changes, the shadow (uncommitted) value of this binding is deleted,
	 * as any value entered by the user is overwritten by the model change. When using this method, make sure
	 * that model changes are properly propagated to the corressponding form field. 
	 */
	public <T> ObservableValueBinding<T> wrap(ObservableValueBinding<T> binding) {
		return new ObservableWrappedFormBinding<T>(binding);
	}
	
	/**
	 * Flushes all shadow values to the actual models
	 */
	public void commit() {
		for (ShadowValueHolder holder : holders) {
			holder.commit();
		}
	}
	
	/**
	 * Discards all shadow values so that the bindings will reflect the actual model values
	 */
	public void cancel() {
		for (ShadowValueHolder holder : holders) {
			holder.cancel();
		}
	}
	
	protected interface ShadowValueHolder {
		public void commit();
		public void cancel();
	}
	
	protected class WrappedFormBinding<T> implements ValueBinding<T>, ShadowValueHolder {
		protected ValueBinding<T> wrappedBinding;
		protected T shadowValue;
		protected boolean shadowValueSet = false;
		public WrappedFormBinding(ValueBinding<T> wrappedBinding) {
			Preconditions.checkNotNull(wrappedBinding);
			this.wrappedBinding = wrappedBinding;
			holders.add(this);
		}
		@Override
		public T getValue() {
			if (!shadowValueSet) {
				return wrappedBinding.getValue();
			}
			return shadowValue;
		}
		@Override
		public void setValue(T value) {
			shadowValue = value;
			shadowValueSet = true;
		}
		@Override
		public void commit() {
			if (shadowValueSet) {
				wrappedBinding.setValue(shadowValue);
				shadowValueSet = false;
				shadowValue = null;
			}
		}
		@Override
		public void cancel() {
			shadowValue = null;
			shadowValueSet = false;
		}
	}
	
	protected class ObservableWrappedFormBinding<T> extends WrappedFormBinding<T> implements ObservableValueBinding<T> {
		AttributeListeners<T> listeners = new AttributeListeners<>();
		public ObservableWrappedFormBinding(ObservableValueBinding<T> wrappedBinding) {
			super(wrappedBinding);
			wrappedBinding.addValueChangeListener(new ValueChangeListener<T>() {
				@Override
				public void valueChanged(T newValue) {
					// if the value changes, we suppose that any value entered to form fields by the user gets overwritten
					// by the new value in the model, so the shadow value (that was entered by the user) should be removed
					shadowValueSet = false;
					shadowValue = null;
				}
			});
		}
		@Override
		public ListenerRegistration addValueChangeListener(final ValueChangeListener<T> listener) {
			final ListenerRegistration shadowRegistration = listeners.addListener(listener);
			final ListenerRegistration actualRegistration = ((ObservableValueBinding<T>)wrappedBinding).addValueChangeListener(listener);
			return new ListenerRegistration() {
				@Override
				public void removeHandler() {
					shadowRegistration.removeHandler();
					actualRegistration.removeHandler();
				}
			};
		}
		@Override
		public void cancel() {
			boolean fireListeners = shadowValueSet;
			super.cancel();
			if (fireListeners) {
				listeners.fireListeners(wrappedBinding.getValue());
			}
		}
	}

}
