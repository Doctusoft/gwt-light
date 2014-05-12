package com.doctusoft.gwt.light;

import lombok.Getter;

import com.doctusoft.gwt.light.client.ExampleActivityMapper;
import com.doctusoft.gwt.light.client.index.IndexActivity;
import com.doctusoft.gwt.light.client.list.ListActivity;
import com.doctusoft.gwt.light.client.list.NewEntityModalDialogPresenter;
import com.doctusoft.gwt.light.client.list.TestRemoteServiceAsync;
import com.doctusoft.gwt.light.mvp.AbstractIntegartionClientFactory;
import com.doctusoft.gwt.light.mvp.IntegrationServiceFactory;
import com.doctusoft.gwt.light.mvp.TotallyDummyEmptyView;
import com.doctusoft.gwt.light.mvp.ViewOf;
import com.doctusoft.gwt.light.server.TestServiceRPC;
import com.doctusoft.gwt.light.shared.ClientFactory;
import com.google.gwt.activity.shared.ActivityMapper;

public class MockClientFactoryImpl extends AbstractIntegartionClientFactory implements ClientFactory {

	@Override
	protected ActivityMapper createActivityMapper() {
		return new ExampleActivityMapper(this);
	}
	
	@Getter
	private ViewOf<IndexActivity> indexView = new TotallyDummyEmptyView<>();

	@Getter
	private ViewOf<ListActivity> listView = new TotallyDummyEmptyView<>();

	@Getter
	private ViewOf<NewEntityModalDialogPresenter> newEntityModalDialog = new TotallyDummyEmptyView<>();

	/**
	 * Normally here you'd use a Guice injector or something to get reference to the RPC implementation
	 */
	@Getter
	private TestRemoteServiceAsync testRemoteServiceAsync = IntegrationServiceFactory.getService(TestRemoteServiceAsync.class,
			new TestServiceRPC());


}
