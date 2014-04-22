package com.doctusoft.gwt.light.client.index;

import com.doctusoft.gwt.light.mvp.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class IndexPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<IndexPlace> {
		public Tokenizer() {
			super(new IndexPlace());
		}
	}

}
