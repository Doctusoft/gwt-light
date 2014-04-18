package com.doctusoft.gwt.light;

import java.util.List;

import com.doctusoft.common.core.bean.ValueChangeListener;
import com.doctusoft.common.core.bean.binding.ValueBinding;
import com.doctusoft.common.core.bean.binding.observable.ObservableValueBinding;
import com.google.gwt.dom.client.Element;
import com.xedge.jquery.client.JQuery;

/**
 * Repeats the first child with class 'light-repeat-item'
 * 
 */
public abstract class LightList<T> extends AbstractLightWidget<LightList<T>> {
	
	protected String itemTemplate;
	protected JQuery listParent;
	
	protected List<T> data;

	public LightList(final JQuery selector) {
		super(selector);
		initList();
	}

	public LightList(final String selector) {
		this(JQuery.select(selector));
	}
	
	private void initList() {
		JQuery temp = JQuery.select("<div/>");
		JQuery templateItem = root.find(".light-repeat-item").first();
		listParent = templateItem.parent();
		templateItem.appendTo(temp);
		itemTemplate = temp.get(0).getInnerHTML();
		templateItem.remove();
	}
	
	public void setData(List<T> data) {
		if (data != null) {
			this.data = data;
			reRender();
		}
	}
	
	public LightList<T> bind(final ValueBinding<? extends List<T>> binding) {
		setData(binding.getValue());
		if (binding instanceof ObservableValueBinding<?>) {
			((ObservableValueBinding<List<T>>) binding).addValueChangeListener(new ValueChangeListener<List<T>>() {
				@Override
				public void valueChanged(List<T> value) {
					setData(value);
				}
			});
		}
		return this;
	}

	/**
	 * This is like 100x faster (well that used to be with gwtquery, now with gwt-jquery it's probably okay
	 */
	protected native void nativeRemoveChildren(Element element) /*-{
		$wnd.$(element).children().remove();
	}-*/;
	
	public void reRender() {
		nativeRemoveChildren(listParent.get(0));
		int count = 0;
		for (T item : data) {
			JQuery itemElement = JQuery.select(itemTemplate);
			renderItem(itemElement, item, count);
			itemElement.appendTo(listParent);
			count ++;
		}
	}
	
	/**
	 * Override this method to create light components for this row.
	 * Note that you cannot bind to the repeated row element itself ( for a row "<span><a/></span>", row.find("span") won't find the element, but find("a") will ) 
	 */
	protected abstract void renderItem(JQuery row, T item, int number);
}
