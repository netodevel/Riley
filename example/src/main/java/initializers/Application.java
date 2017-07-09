package initializers;

import br.com.rileyframework.Riley;
import br.com.rileyframework.servers.Servers;

public class Application {

	public static void main(String[] args) throws Exception {
		new Riley().init(Servers.JETTY);
	}
	
}
