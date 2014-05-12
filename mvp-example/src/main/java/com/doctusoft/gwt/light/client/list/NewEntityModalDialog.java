package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.LightButton;
import com.doctusoft.gwt.light.LightTextbox;
import com.doctusoft.gwt.light.ModalDialogBaseWithPresenter;
import com.doctusoft.gwt.light.shared.TestEntityDTO_;
import com.google.gwt.i18n.client.ConstantsWithLookup;

public class NewEntityModalDialog extends ModalDialogBaseWithPresenter<NewEntityModalDialogPresenter> {
	
	public NewEntityModalDialog() {
		super("newEntityModal", "templates/NewEntityModalDialog.html");
		headerString("Edit entity");
	}
	
	@Override
	protected void onDialogContentLoaded() {
		new LightTextbox("#entityName").bind(NewEntityModalDialogPresenter.form.wrap(bindOnPresenter().get(NewEntityModalDialogPresenter_._dto).get(TestEntityDTO_._name)));
		new LightButton("#okButton").click(presenterMethod(NewEntityModalDialogPresenter_.__save));
		new LightButton("#cancelButton").click(presenterMethod(NewEntityModalDialogPresenter_.__cancel));
	}
	
	@Override
	protected ConstantsWithLookup getMessages() {
		return null;	// no translations
	}

}
