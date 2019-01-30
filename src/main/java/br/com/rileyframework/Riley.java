package br.com.rileyframework;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouteRegistry;
import br.com.rileyframework.sample.HelloWorldController;
import com.riley.server.ConfigureServerAdapter;
import com.riley.server.JettyServer;
import com.riley.server.RileyServerException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static br.com.rileyframework.RileyConst.DEFAULT_BANNER;

public class Riley {

	public static RouteManager routeManager = new RouteManager();
	public static RouteRegistry routeRegistry = new RouteRegistry();
	private static Riley instance;

	private String bannerText;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ConfigureServerAdapter configureServerAdapter;

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
		HelloWorldController helloWorldController = new HelloWorldController();
	}

	public void start() throws Exception {
		if (this.configureServerAdapter == null) {
			this.configureServerAdapter = getServerDefault();
		}
		validateServer();
		printBanner();
		System.setErr(new PrintStream(errContent));
		System.out.println("Starting server...");

		this.configureServerAdapter.start();
		while (!this.configureServerAdapter.isStarted()) {}

		System.out.println("Riley Application started in development on http://localhost:" + this.configureServerAdapter.port());

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
		Server server = new Server();
		server.setPort(this.configureServerAdapter.port());
		server.setIsStaterd(this.configureServerAdapter.isStarted());
		server.setServlet(this.configureServerAdapter.servlet());
		return server;
	}

	public Riley bannerText(String banner) {
		this.bannerText = banner;
		return this;
	}

}
