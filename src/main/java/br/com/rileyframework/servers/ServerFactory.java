package br.com.rileyframework.servers;

import br.com.rileyframework.server.RileyServerException;

/**
 * define server embedded
 * @author neto
 */
public class ServerFactory {

	/**
	 * Create server 
	 * @param typeServer
	 * @throws Exception
	 */
	public void create(String typeServer) throws Exception {
		if (Servers.JETTY.equals(typeServer)) {
			JettyServer.init();
		} else {
			throw new RileyServerException("server not implemented");
		}
	}

	public void shutdownServer(String typeServer) throws Exception {
		if (Servers.JETTY.equals(typeServer)) {
			JettyServer.shutdown();
		}
	}
	
}
