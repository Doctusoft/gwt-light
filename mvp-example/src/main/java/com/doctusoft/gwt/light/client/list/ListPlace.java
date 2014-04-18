package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.shared.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class ListPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<ListPlace> {
		public Tokenizer() {
			super(new ListPlace());
		}
	}
	

}
