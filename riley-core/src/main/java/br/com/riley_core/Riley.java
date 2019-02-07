package br.com.riley_core;

import br.com.riley.router.RouteManager;
import br.com.riley_core.domain.Server;
import br.com.riley_core.output.RileyOutput;
import br.com.riley_core.sample.HelloWorldController;
import br.com.riley_core.servlet.RileyServlet;
import com.riley.server.ConfigureServerAdapter;
import com.riley.server.JettyServer;
import com.riley.server.RileyServerException;

import static br.com.riley_core.output.RileyOutput.DEFAULT_BANNER;

public class Riley {

	/**
	 * Manager Routes
	 */
	public static RouteManager routeManager = new RouteManager();

	/**
	 * Singleton instance of Riley
	 */
	private static Riley instance;

	/**
	 * Attribute to custom/default banner
	 */
	private String bannerText;

	/**
	 * Boundary to http server
	 */
	private ConfigureServerAdapter configureServerAdapter;

	/**
	 * Riley Output
	 */
	private RileyOutput rileyOutput = new RileyOutput();

	public static synchronized Riley getInstance(){
		if (instance == null){
			instance = new Riley();
		}
		return instance;
	}

	public void configureServer(ConfigureServerAdapter configureServerAdapter) {
		this.configureServerAdapter = configureServerAdapter;
	}

	public void registerRoutes() {
		//TODO: Ler todos controllers
		new HelloWorldController();
	}

	public void start() throws Exception {
		if (this.configureServerAdapter == null) {
			this.configureServerAdapter = getServerDefault();
		}

		validateServer();
		printBanner();
		rileyOutput.captureOutput();

		this.configureServerAdapter.start();

		while (!this.configureServerAdapter.isStarted()) {
		}
		System.out.println("[INFO] Riley Application started in development on http://localhost:" + this.configureServerAdapter.port());
		registerRoutes();
	}

	private void printBanner() {
		if (bannerText != null) System.out.println(bannerText);
		if (bannerText == null) System.out.println(DEFAULT_BANNER);
	}

	public void validateServer() {
		if (this.configureServerAdapter.port() == null){
			this.configureServerAdapter = null;
			throw new IllegalArgumentException("Port can not be null");
		}
		if (this.configureServerAdapter.servlet() == null){
			this.configureServerAdapter = null;
			throw new IllegalArgumentException("Servlet can not be null");
		}
	}

	protected ConfigureServerAdapter getServerDefault() {
		return JettyServer.builder()
				.servlet(new RileyServlet(Riley.getInstance()))
				.jettyPort(3000)
				.build();
	}

	public void shutDown() throws Exception {
		if (isNotValidToShutdownServer()) throw new RileyServerException("Server not started");
		this.configureServerAdapter.shutDown();
		this.configureServerAdapter = null;
	}

	private boolean isNotValidToShutdownServer() {
		if (this.configureServerAdapter == null || !configureServerAdapter.isStarted()) return true;
		return false;
	}

	public Server getServer() {
		return Server.builder()
				.port(this.configureServerAdapter.port())
				.isStaterd(this.configureServerAdapter.isStarted())
				.servlet(this.configureServerAdapter.servlet())
				.build();
	}

	public Riley bannerText(String banner) {
		this.bannerText = banner;
		return this;
	}

}
