package com.doctusoft.gwt.light;

import lombok.ObservableAttribute;

import com.doctusoft.common.core.bean.ParametricClassMethodReferences.ClassMethodReference0;
import com.doctusoft.common.core.bean.ParametricClassMethodReferences.ClassMethodReference1;
import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.observable.ObservableChainedValueBindingBuilder;
import com.google.gwt.dom.client.Element;
import com.xedge.jquery.client.JQuery;

public abstract class LightPanelWithPresenter<Presenter> extends AbstractLightPanel {
	
	@ObservableAttribute(staticField=false)
	protected Presenter presenter;

	public LightPanelWithPresenter(final Element element) {
		super(JQuery.select(element));
	}

	public LightPanelWithPresenter(final JQuery selector) {
		super(selector);
	}

	public LightPanelWithPresenter(final String selector) {
		super(selector);
	}
	
	/**
	 * Override this method do perform actions when the view is (re)presented for the user.
	 * This method is invoked also the first time the view is presented, but only after the view content has been loaded.
	 */
	protected void onViewPresented() {
		
	}
	
	/**
	 * Don't override this method on normal cases. Override 'onViewPresented'. This method might need to be overriden
	 * when extending fundamental functionality of this class.
	 */
	public void viewPresented() {
		afterViewLoaded(new ViewLoadedListener() {
			@Override
			public void viewLoaded(AsyncLoadedView view) {
				onViewPresented();
			}
		});
	}
	
	protected ObservableChainedValueBindingBuilder<Presenter> bindOnPresenter() {
		return ObservableChainedValueBindingBuilder.on(this).get((com.doctusoft.common.core.bean.ObservableAttribute<LightPanelWithPresenter<Presenter>, Presenter>)(Object)_presenter);
	}

	protected EmptyEventListener presenterMethod(final ClassMethodReference0<? super Presenter, Void> methodRef) {
		return new EmptyEventListener() {
			
			@Override
			public void handle() {
				methodRef.apply(presenter);
			}
		};
	}
	
	protected <Param> EmptyEventListener presenterMethod(final ClassMethodReference1<? super Presenter, Void, Param> methodRef, final Param parameter) {
		return new EmptyEventListener() {
			@Override
			public void handle() {
				methodRef.apply(presenter, parameter);
			}
		};
	}
	
	protected <Param, View extends LightPanelWithPresenter<Presenter>> EmptyEventListener presenterMethod(final ClassMethodReference1<Presenter, Void, Param> methodRef, final ClassMethodReference0<View, Param> methodRef2) {
		return new EmptyEventListener() {
			@Override
			public void handle() {
				ClassMethodReference0<LightPanelWithPresenter<Presenter>, Param> methodRef3 = (ClassMethodReference0<LightPanelWithPresenter<Presenter>, Param>) methodRef2;
				methodRef.apply(presenter, methodRef3.apply((View)LightPanelWithPresenter.this));
			}
		};
	}
	
	protected <Param> EmptyEventListener rowActionListener(final ClassMethodReference1<Presenter, Void, Param> methodRef, final Param parameter) {
		return new EmptyEventListener() {
			
			@Override
			public void handle() {
				methodRef.apply(presenter, parameter);
			}
		};
	}
	
	protected EmptyEventListener eventListener(final ClassMethodReference0<Presenter, Void> methodRef) {
		return new EmptyEventListener() {
			@Override
			public void handle() {
				methodRef.apply(presenter);
			}
		};
	}
	
	protected ValueChangeListener valueChangeListener(final ClassMethodReference0<Presenter, Void> methodRef) {
		return new ValueChangeListener() {
			@Override
			public void valueChanged(Object value) {
				methodRef.apply(presenter);
			}
		};
	}
	
	protected <ViewType extends LightPanelWithPresenter<Presenter>> EmptyEventListener viewMethod(final ClassMethodReference0<ViewType, Void> methodRef) {
		return new EmptyEventListener() {
			@Override
			public void handle() {
				methodRef.apply((ViewType) LightPanelWithPresenter.this);
			}
		};
	}
}
