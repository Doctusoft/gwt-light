package com.doctusoft.gwt.light.client.util;

import com.doctusoft.gwt.light.LightPanelWithPresenter;
import com.doctusoft.gwt.light.shared.ViewOf;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.xedge.jquery.client.JQuery;

public abstract class BaseView<Presenter> extends LightPanelWithPresenter<Presenter> implements ViewOf<Presenter> {
	
	public BaseView(String remoteTemplate) {
		super(JQuery.select("<div/>"));
		remoteTemplate(remoteTemplate, true);
	}
	
	@Override
	protected ConstantsWithLookup getMessages() {
		// no i18n in this example
		return null;
	}

}
