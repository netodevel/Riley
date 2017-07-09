package br.com.rileyframework;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RileyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Riley riley;
	
	private List<Route> listRoutes;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		riley = new Riley();

		try {
			listRoutes = riley.registerControllers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	/**
	 * Handler request.
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		final String servletPath = req.getServletPath();
		for (Route route : listRoutes) {
			if (route.getRoute().equals(servletPath)) {
				route.getHandler().handler(req, resp);
			}
		}
		
//		for (UrlMapping urlMapped : rileyFramework.getMappings()) {
//			if (rileyFramework.matchUrl(urlMapped.getRegex(), servletPath)) {
//				httpCommands.invokeAction(servletPath, req, resp, urlMapped);
//				break;
//			}
//		}
	}
	
}
