package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.mvp.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class ListPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<ListPlace> {
		public Tokenizer() {
			super(new ListPlace());
		}
	}
	

}
