package com.doctusoft.gwt.light.index;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.doctusoft.gwt.light.AbstractIntegrationTest;
import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.index.IndexPlace;

public class TestIndexPage extends AbstractIntegrationTest {
	
	@Test
	public void testGreeting() {
		app.getPlaceController().goTo(new IndexPlace());
		IndexActivity activity = on(IndexActivity.class);
		activity.setName("John");
		activity.greet();
		assertEquals("Hello: John", activity.getGreetingLabel());
	}

}
