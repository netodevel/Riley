package br.com.rileyframework.server;

import lombok.Builder;
import lombok.Data;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@Data
@Builder
public class JettyServer implements ConfigureServerAdapter {

	private static final Integer DEFAULT_PORT = 3000;
	private Server server;
	private Integer jettyPort;

	@Override
	public void start() throws Exception {
		server = new Server(getServerPort());
		HandlerList handlers = new HandlerList();

		ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
		sch.setContextPath("/");

		sch.addServlet(new ServletHolder(servlet()), "/");

		handlers.setHandlers(new Handler[] { sch, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();
	}

	@Override
	public void shutDown() throws Exception {
		server.stop();
	}

	@Override
	public Integer port() {
		return getServerPort();
	}

	@Override
	public Boolean isStarted() {
		return server.isStarted();
	}

	public Integer getServerPort() {
		return this.getJettyPort() != null ? this.getJettyPort() : DEFAULT_PORT;
	}

}
