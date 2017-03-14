package br.com.rileyframework;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.utils.SetupRiley;

/**
 * handler of all requests
 * 
 * @author neto
 */
public class RileyFrontController extends HttpServlet {

	private static final String DIR_BASE_PACKAGE = "src/main/resources/setup.conf";
	
	private static final long serialVersionUID = 1L;

	private RileyFramework rileyFramework;
	
	private HttpCommands httpCommands;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rileyFramework = new RileyFramework();
		httpCommands = new HttpCommands();
		String basePackage = SetupRiley.getBasePackage(new File(DIR_BASE_PACKAGE));
		try {
			rileyFramework.scanMappings(basePackage);
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
		for (UrlMapping urlMapped : rileyFramework.getMappings()) {
			if (rileyFramework.matchUrl(urlMapped.getRegex(), servletPath)) {
				httpCommands.invokeAction(servletPath, req, resp, urlMapped);
				break;
			}
		}
	}

}
