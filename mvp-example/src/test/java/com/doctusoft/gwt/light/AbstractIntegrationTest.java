package com.doctusoft.gwt.light;

import org.junit.After;
import org.junit.Before;

import com.google.common.base.Preconditions;
import com.google.gwt.activity.shared.Activity;

public abstract class AbstractIntegrationTest {
	
	protected JvmEntryPoint app;

	@Before
	public void setup() {
		app = new JvmEntryPoint();
	}
	
	@After
	public void teardown() {
		app = null;
	}

	protected <T extends Activity> T on(Class<T> activityClass) {
		Activity activity = app.getClientFactory().getCurrentPresenter();
		Preconditions.checkNotNull(activity);
		Preconditions.checkState(activity.getClass().equals(activityClass));
		return (T) activity;
	}

}
