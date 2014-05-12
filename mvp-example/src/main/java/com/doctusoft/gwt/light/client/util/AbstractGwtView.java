package com.doctusoft.gwt.light.client.util;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference0;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.gwt.light.EmptyEventListener;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Note that extendidng {@link HorizontalPanel} might not be the best idea, but it's common for our example views
 */
public abstract class AbstractGwtView<Presenter> extends HorizontalPanel implements ViewOf<Presenter> {
	
	@ObservableProperty
	private Presenter presenter;
	
	@Override
	public void viewPresented() {
		
	}
	
	protected ObservableChainedValueBindingBuilder<Presenter> bindOnPresenter() {
		return Bindings.obs(this).get((com.doctusoft.bean.ObservableProperty)AbstractGwtView_._presenter);
	}

	protected EmptyEventListener presenterMethod(final ClassMethodReference0<? super Presenter, Void> methodRef) {
		return new EmptyEventListener() {
			
			@Override
			public void handle() {
				methodRef.apply(presenter);
			}
		};
	}

}
