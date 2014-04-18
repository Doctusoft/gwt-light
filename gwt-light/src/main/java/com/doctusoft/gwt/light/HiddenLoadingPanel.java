package com.doctusoft.gwt.light;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.xedge.jquery.client.JQuery;


public abstract class HiddenLoadingPanel extends AbstractLightPanel<HiddenLoadingPanel> {
	
	private ConstantsWithLookup messages;

	public HiddenLoadingPanel(String remoteTemplateUrl, ConstantsWithLookup messages) {
		super(JQuery.select("<div/>"));
		this.messages = messages;
		root.css("display", "none");
		JQuery.select("body").append(root);
		remoteTemplate(remoteTemplateUrl, false);
	}
	
	protected void dispose() {
		// this removes the temp container element from the DOM
		root.remove();
	}
	
	@Override
	protected ConstantsWithLookup getMessages() {
		return messages;
	}
	
}
