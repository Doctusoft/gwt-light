package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TestRemoteServiceAsync {

	void itemAdded(TestEntityDTO dto, AsyncCallback<Void> callback);

	void itemRemoved(TestEntityDTO dto, AsyncCallback<Void> callback);

}
