package com.doctusoft.gwt.light.client.list;

import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("testservice")
public interface TestRemoteService extends RemoteService {
	
	public void itemAdded(TestEntityDTO dto);
	
	public void itemRemoved(TestEntityDTO dto);

}
