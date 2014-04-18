package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.ModalDialogBaseWithPresenter;
import com.google.gwt.i18n.client.ConstantsWithLookup;

public class NewEntityModalDialog extends ModalDialogBaseWithPresenter<NewEntityModalDialogPresenter> {
	
	public NewEntityModalDialog() {
		super("newEntityModal", "templates/NewEntityModalDialog.html");
	}
	
	@Override
	protected void onDialogContentLoaded() {
		
	}
	
	@Override
	protected ConstantsWithLookup getMessages() {
		return null;	// no translations
	}

}
