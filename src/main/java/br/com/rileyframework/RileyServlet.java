package br.com.rileyframework;

import br.com.rileyframework.verbs.HttpVerbProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RileyServlet extends HttpServlet {

	private Riley riley;
	private HttpVerbProcessor httpVerbProcessor;

	public RileyServlet(Riley riley) {
		this.riley = riley;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		httpVerbProcessor = new HttpVerbProcessor();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String servletPath = req.getServletPath();

		System.out.println(servletPath);
		riley.routeRegistry.routes.forEach(System.out::println);
	}

}
