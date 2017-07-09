package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.rileyframework.RileyFrameworkBuilder;

public class HomeController extends RileyFrameworkBuilder {
	
	{
		get("/home", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("home...");
			}
		});
		
		get("/index2", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("index2...");
			}
		});
	}
	
	

}
