package br.com.rileyframework;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			//doProcess(req, resp);
			StringBuffer jb = new StringBuffer();
			  String line = null;
			  try {
			    BufferedReader reader = req.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }

			  System.out.println("post" + jb);
			  
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
			if (matchUrl(route.getRouteRegex(), servletPath)) {

				Request request = buildRequest(servletPath, route);
				
				route.getHandler().handler(request, resp);
			}
		}
	}

	private Request buildRequest(final String servletPath, Route route) {
		Request request = new Request();
		request.setPathVariables(getPathVariables(route.getRoute(), servletPath));
		return request;
	}
	
	/**
	 * get path variables url
	 * @return 
	 */
	private HashMap<String, String> getPathVariables(String url, String contextPath) {
		String[] paramName = url.split("\\/\\w*\\/");
		String[] paramValue = contextPath.split("\\/\\w*\\/");
		HashMap<String, String> pathVariables = new HashMap<>();

		for (int i = 0; i < paramValue.length; i++) {
			if (paramValue[i].equals("") || paramValue[i].equals(null)) {
				continue;
			} else {
				pathVariables.put(paramName[i], paramValue[i]);
			}
		}
		
		return pathVariables;
	}
	
	public boolean matchUrl(String regex, String urlOrigin) {
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(urlOrigin);
		return m.matches();
	}
	
}
