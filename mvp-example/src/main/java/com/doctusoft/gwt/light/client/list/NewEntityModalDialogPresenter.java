package com.doctusoft.gwt.light.client.list;

import lombok.MethodRef;
import lombok.ObservableAttribute;

import com.doctusoft.gwt.light.AbstractModalPresenter;
import com.doctusoft.gwt.light.ShadowForm;
import com.doctusoft.gwt.light.shared.TestEntityDTO;

public class NewEntityModalDialogPresenter extends AbstractModalPresenter {
	
	@ObservableAttribute
	private TestEntityDTO dto;
	
	// as the view is a singleton, we declare the form static. A better approach might be
	//  to instantiate the form through the ClientFactory
	public static ShadowForm form = new ShadowForm();
	
	@MethodRef
	public void save() {
		form.commit();
		setVisible(false);
	}
	
	@MethodRef
	public void cancel() {
		form.cancel();
		setVisible(false);
	}

}
