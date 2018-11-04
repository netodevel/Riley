package br.com.rileyframework.servers;

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
			throw new Exception("server not implemented");
		}
	}
	
}
