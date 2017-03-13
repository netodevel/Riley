package br.com.rileyframework.servers;

/**
 * define engine server embedded
 * 
 * @author neto
 */
public class ServerFactory {

	/**
	 * Create server 
	 * @param typeServer
	 * @throws Exception
	 */
	public void create(String typeServer) throws Exception {
		switch (typeServer) {
		case Servers.JETTY:
			JettyServer.init();
			break;

		default:
			throw new Exception("Engine not implemented");
		}
		
	}
	
}
