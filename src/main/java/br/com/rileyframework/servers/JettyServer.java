package br.com.rileyframework.servers;

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

		sch.addServlet(new ServletHolder(new RileyServlet()), "/");

		handlers.setHandlers(new Handler[] { sch, new DefaultHandler() });
		server.setHandler(handlers);

		server.start();
		server.join();
	}

}
