package br.com.rileyframework.servers;

import br.com.rileyframework.Riley;
import br.com.rileyframework.RileyServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {

	private static final Server server = new Server(3000);

	protected static void init() throws Exception	{
		HandlerList handlers = new HandlerList();

		ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
		sch.setContextPath("/");

		sch.addServlet(new ServletHolder(new RileyServlet(Riley.getInstance())), "/");

		handlers.setHandlers(new Handler[] { sch, new DefaultHandler() });
		server.setHandler(handlers);

		server.start();
		//server.join();
	}

	protected static void shutdown() throws Exception {
		server.stop();
	}

}
