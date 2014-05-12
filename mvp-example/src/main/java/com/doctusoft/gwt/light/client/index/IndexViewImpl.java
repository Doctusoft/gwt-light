package com.doctusoft.gwt.light.client.index;

import com.doctusoft.gwt.light.LightButton;
import com.doctusoft.gwt.light.LightLabel;
import com.doctusoft.gwt.light.LightTextbox;
import com.doctusoft.gwt.light.client.util.BaseView;

public class IndexViewImpl extends BaseView<IndexActivity> {

	public IndexViewImpl() {
		super("templates/Index.html");
	}
	
	@Override
	protected void onTemplateLoaded() {
		new LightTextbox("#nameInput").bind(bindOnPresenter().get(IndexActivity_._name));
		new LightButton("#greetButton").click(presenterMethod(IndexActivity_.__greet));
		new LightLabel("#greetingText").bindText(bindOnPresenter().get(IndexActivity_._greetingLabel));
	}
	

}
