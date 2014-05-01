package com.doctusoft.gwt.light.client.util;

import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ParametricClassMethodReferences.ClassMethodReference0;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.common.core.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.doctusoft.gwt.light.EmptyEventListener;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Note that extendidng {@link HorizontalPanel} might not be the best idea, but it's common for our example views
 */
public abstract class AbstractGwtView<Presenter> extends HorizontalPanel implements ViewOf<Presenter> {
	
	@ObservableAttribute(staticField=false)
	private Presenter presenter;
	
	@Override
	public void viewPresented() {
		
	}
	
	protected ObservableChainedValueBindingBuilder<Presenter> bindOnPresenter() {
		return Bindings.obs(this).get((com.doctusoft.common.core.bean.ObservableAttribute)(Object)_presenter);
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
