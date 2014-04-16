package com.doctusoft.gwt.light.client;

import com.doctusoft.gwt.light.client.index.IndexPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({IndexPlace.Tokenizer.class})
public interface ExamplePlaceHistoryMapper extends PlaceHistoryMapper {

}
