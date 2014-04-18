package com.doctusoft.gwt.light;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

import com.doctusoft.common.core.bean.Attribute;
import com.doctusoft.common.core.bean.ObservableAttribute;
import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.Bindings;
import com.xedge.jquery.client.JQuery;

public abstract class ModalDialogBaseWithPresenter<Presenter> extends LightPanelWithPresenter<Presenter>
		implements ViewOf<Presenter> {
	
	protected JQuery modalDiv;
	protected JQuery modalContent;
	
	private final String id;
	
	protected List<ViewLoadedListener> dialogLoadedListeners = new ArrayList<ViewLoadedListener>();
	protected boolean dialogLoaded = false;

	@Setter
	protected LightTextbox initialFocusElement;
	
	public ModalDialogBaseWithPresenter(String id, String contentRemoteUrl) {
		super(JQuery.select("<div/>").attr("id", "-modal-container-"+id).appendTo(JQuery.select("body")));	// append the loaded result directly to the document body
		remoteTemplate("templates/comp/ModalDialogBase.html", false);
		this.id = id;
		initDialogBase();
		dialogContentRemote(contentRemoteUrl);
	}
	
	protected void initDialogBase() {
		// remove prior default visibility change listener
		visibilityChangeListener.removeHandler();
		_visible.addChangeListener(this, new ValueChangeListener<Boolean>() {
			boolean oldValue = false;
			public void valueChanged(Boolean visible) {
				if (!oldValue && visible) {
					show();
					if (initialFocusElement != null) {
						initialFocusElement.setFocus();
					}
				} else if (oldValue && !visible){
					hide();
				}
				oldValue = visible;
			};
		});
	}
	
	public void afterDialogLoaded(ViewLoadedListener listener) {
		if (!dialogLoaded) {
			dialogLoadedListeners.add(listener);
		} else {
			listener.viewLoaded(this);
		}
	}

	protected void fireDialogLoadedListeners() {
		for (ViewLoadedListener listener : dialogLoadedListeners) {
			listener.viewLoaded(this);
		}
		dialogLoaded = true;
	}
	
	protected void disableClose() {
		afterViewLoaded(new ViewLoadedListener() {
			@Override
			public void viewLoaded(AsyncLoadedView view) {
				modalDiv.find("button.close").hide();
			}
		});
	}

	public void headerKey(final String key) {
		afterViewLoaded(new ViewLoadedListener() {
			@Override
			public void viewLoaded(AsyncLoadedView view) {
				modalDiv.find(".modal-header-label").text(getMessages().getString(key.replace('.', '_')));
			}
		});
	}
	
	@Override
	protected void postProcessTemplate() {
		modalDiv = root.find("div.modal").attr("id", id);
		modalContent = modalDiv.find(".modal-content");
	}
	
	@Override
	protected void onTemplateLoaded() {
	}
	
	protected abstract void onDialogContentLoaded();
	
	protected void dialogContentRemote(String url) {
		new HiddenLoadingPanel(url, getMessages()) {
			
			@Override
			protected void onTemplateLoaded() {
				ModalDialogBaseWithPresenter.this.afterViewLoaded(new ViewLoadedListener() {
					@Override
					public void viewLoaded(AsyncLoadedView view) {
						root.children().appendTo(modalContent);
						dispose();
						if (getPresenter() instanceof AbstractModalPresenter) {
							Bindings.bind(bindOnPresenter().get((ObservableAttribute)((AbstractModalPresenter)getPresenter())._visible),
									Bindings.on(ModalDialogBaseWithPresenter.this).get((Attribute)_visible));
						}
						onDialogContentLoaded();
						fireDialogLoadedListeners();
					}
				});
			}
		};
	}
	
	public void show() {
		_show(id);
	}

	public void hide() {
		_hide(id);
	}
	
	protected native void _show(String id) /*-{
		$wnd.$("#" + id).modal('show');
	}-*/;

	protected native void _hide(String id) /*-{
		$wnd.$("#" + id).modal('hide');
	}-*/;

}