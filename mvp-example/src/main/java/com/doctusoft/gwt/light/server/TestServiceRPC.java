package com.doctusoft.gwt.light.server;

import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.doctusoft.gwt.light.client.list.TestRemoteService;
import com.doctusoft.gwt.light.shared.TestEntityDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@WebServlet("testservice")
public class TestServiceRPC extends RemoteServiceServlet implements TestRemoteService {
	
	private static final Logger log = Logger.getLogger(TestServiceRPC.class.getName());

	@Override
	public void itemAdded(TestEntityDTO dto) {
		// these methods do actually nothing, they're just here to present integration testing with RPC
		log.info("Entity added: " + dto);
	}

	@Override
	public void itemRemoved(TestEntityDTO dto) {
		// these methods do actually nothing, they're just here to present integration testing with RPC
		log.info("Entity removed: " + dto);
	}
	

}
