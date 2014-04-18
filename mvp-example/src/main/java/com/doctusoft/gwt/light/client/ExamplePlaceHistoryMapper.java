package com.doctusoft.gwt.light.client;

import com.doctusoft.gwt.light.client.index.IndexPlace;
import com.doctusoft.gwt.light.client.list.ListPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({IndexPlace.Tokenizer.class, ListPlace.Tokenizer.class})
public interface ExamplePlaceHistoryMapper extends PlaceHistoryMapper {

}
