package br.com.rileyframework.servers;

import br.com.rileyframework.RxRileyServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import br.com.rileyframework.RileyServlet;

public class JettyServer {

	public static void init() throws Exception	{
		Server server = new Server(3000);
		HandlerList handlers = new HandlerList();

		ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
		sch.setContextPath("/");

		sch.addServlet(new ServletHolder(new RxRileyServlet()), "/");

		handlers.setHandlers(new Handler[] { sch, new DefaultHandler() });
		server.setHandler(handlers);

		server.start();
		server.join();
	}

}
