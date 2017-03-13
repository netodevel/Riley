package br.com.rileyframework;

import br.com.rileyframework.servers.Servers;

public class MyApplication {

	public static void main(String[] args) {
		new RileyFramework().init(MyApplication.class, Servers.JETTY);
	}
	
}
