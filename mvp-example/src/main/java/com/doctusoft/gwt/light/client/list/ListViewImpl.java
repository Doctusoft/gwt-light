package com.doctusoft.gwt.light.client.list;

import com.doctusoft.common.core.bean.binding.Bindings;
import com.doctusoft.gwt.light.LightButton;
import com.doctusoft.gwt.light.LightLabel;
import com.doctusoft.gwt.light.LightList;
import com.doctusoft.gwt.light.client.util.BaseView;
import com.doctusoft.gwt.light.client.util.LongConverter;
import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.xedge.jquery.client.JQuery;

public class ListViewImpl extends BaseView<ListActivity> {
	
	public ListViewImpl() {
		super("templates/List.html");
	}
	
	@Override
	protected void onTemplateLoaded() {
		new LightList<TestEntityDTO>("#entityList") {
			@Override
			protected void renderItem(JQuery row, TestEntityDTO item, int number) {
				new LightLabel(row.find(".entityId")).bindText(Bindings.obs(item).get(TestEntityDTO._id).convert(new LongConverter()));
				new LightLabel(row.find(".entityName")).bindText(Bindings.obs(item).get(TestEntityDTO._name));
			}
		}.bind(bindOnPresenter().get(ListActivity._testEntities));
		
		new LightButton("#addNewEntityButton").click(presenterMethod(ListActivity.__addNewEntity));
	}

}
