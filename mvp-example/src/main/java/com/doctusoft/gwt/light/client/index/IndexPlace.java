package com.doctusoft.gwt.light.client.index;

import com.doctusoft.gwt.light.shared.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class IndexPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<IndexPlace> {
		public Tokenizer() {
			super(new IndexPlace());
		}
	}

}
