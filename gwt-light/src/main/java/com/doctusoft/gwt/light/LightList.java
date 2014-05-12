package com.doctusoft.gwt.light;

import java.util.ArrayList;
import java.util.List;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.Property;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableList.ListElementInsertedListener;
import com.doctusoft.bean.binding.observable.ObservableList.ListElementRemovedListener;
import com.google.gwt.dom.client.Element;
import com.xedge.jquery.client.JQuery;

/**
 * Repeats the first child with class 'light-repeat-item'
 * 
 */
public abstract class LightList<T> extends AbstractLightWidget<LightList<T>> {
	
	protected String itemTemplate;
	protected JQuery listParent;
	
	@ObservableProperty
	protected List<T> data;
	
	protected List<JQuery> itemElements = new ArrayList<>();
	private ListenerRegistration insertListener;
	private ListenerRegistration deleteListener;

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
		LightList_._data.addChangeListener(this, new ValueChangeListener<List<?>>() {
			@Override
			public void valueChanged(List<?> newValue) {
				// if the entire list refernce changes, we rerender everything
				reRender();
				attachNewList();
			}
		});
	}
	
	protected void attachNewList() {
		// remove any previous listeners
		if (insertListener != null) {
			insertListener.removeHandler();
			insertListener = null;
		}
		if (deleteListener != null) {
			deleteListener.removeHandler();
			deleteListener = null;
		}
		if (data instanceof ObservableList<?>) {
			ObservableList<T> observableList = (ObservableList<T>) data;
			insertListener = observableList.addInsertListener(new ListElementInsertedListener<T>() {
				@Override
				public void inserted(ObservableList<T> list, int index, T element) {
					JQuery itemElement = JQuery.select(itemTemplate);
					renderItem(itemElement, element, index);
					if (index >= itemElements.size()) {
						// insert as the last item
						itemElements.add(itemElement);
						itemElement.appendTo(listParent);
					} else {
						// insert at a specific position
						itemElements.get(index).insertBefore(itemElement);
						itemElements.add(index, itemElement);
					}
				}
			});
			deleteListener = observableList.addDeleteListener(new ListElementRemovedListener<T>() {
				@Override
				public void removed(ObservableList<T> list, int index, T element) {
					// remove the element from the dom
					itemElements.get(index).remove();
					// remove the element from the list
					itemElements.remove(index);
				}
			});
		}
	}
	
	public LightList<T> bind(final ValueBinding<? extends List<T>> binding) {
		Bindings.bind(binding, Bindings.on(this).get((Property)LightList_._data));
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
		if (data == null)
			return;
		for (T item : data) {
			JQuery itemElement = JQuery.select(itemTemplate);
			renderItem(itemElement, item, count);
			itemElement.appendTo(listParent);
			itemElements.add(itemElement);
			count ++;
		}
	}
	
	/**
	 * Override this method to create light components for this row.
	 * Note that you cannot bind to the repeated row element itself ( for a row "<span><a/></span>", row.find("span") won't find the element, but find("a") will ) 
	 */
	protected abstract void renderItem(JQuery row, T item, int number);
}
