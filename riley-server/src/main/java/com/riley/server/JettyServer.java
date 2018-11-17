package com.riley.server;

import lombok.Builder;
import lombok.Data;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

@Data
@Builder
public class JettyServer implements ConfigureServerAdapter {

	private static final Integer DEFAULT_PORT = 3000;
	private Server 	server;
	private Integer jettyPort;
	private Servlet servlet;

	@Override
	public void start() throws Exception {
		server = new Server(getServerPort());
		HandlerList handlers = new HandlerList();

		ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
		sch.setContextPath("/");

		if (servlet() == null) throw new RileyServerException("Servlet não pode ser nulo");
		sch.addServlet(new ServletHolder(servlet()), "/");

		handlers.setHandlers(new Handler[] { sch, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();
	}

	@Override
	public void shutDown() throws Exception {
		if (!isStarted()) throw new RileyServerException("Server not started");
		server.stop();
	}

	@Override
	public Integer port() {
		return getServerPort();
	}

	@Override
	public Servlet servlet() {
		return servlet;
	}

	@Override
	public Boolean isStarted() {
		if (server == null) return false;
		return server.isStarted();
	}

	public Integer getServerPort() {
		return this.getJettyPort() != null ? this.getJettyPort() : DEFAULT_PORT;
	}

}
