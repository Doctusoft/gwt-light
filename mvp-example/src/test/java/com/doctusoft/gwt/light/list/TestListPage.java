package com.doctusoft.gwt.light.list;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.doctusoft.gwt.light.AbstractIntegrationTest;
import com.doctusoft.gwt.light.TotallyDummyEmptyView;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.doctusoft.gwt.light.client.list.ListPlace;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialogPresenter;
import com.google.gwt.thirdparty.guava.common.base.Strings;

public class TestListPage extends AbstractIntegrationTest {

	@Test
	public void testInsertElement() {
		app.getPlaceController().goTo(new ListPlace());
		ListActivity activity = on(ListActivity.class);
		assertEquals(1, activity.getTestEntities().size());
		activity.addNewEntity();
		// get the dialog's presenter
		NewEntityModalDialogPresenter dialogPresenter =
				((TotallyDummyEmptyView<NewEntityModalDialogPresenter>) app.getClientFactory().getNewEntityModalDialog()).getPresenter();
		assertEquals(true, dialogPresenter.isVisible());
		assertEquals(true, Strings.isNullOrEmpty(dialogPresenter.getDto().getName()));
		dialogPresenter.getDto().setName("entity name");
		dialogPresenter.save();
		// at this point, the RPC service was also invoked, but now we don't assert on that
		assertEquals(false, dialogPresenter.isVisible());
		assertEquals(2, activity.getTestEntities().size());
		assertEquals("entity name", activity.getTestEntities().get(1).getName());
	}
}
