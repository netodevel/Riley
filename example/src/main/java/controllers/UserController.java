package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.RileyFrameworkBuilder;

public class UserController extends RileyFrameworkBuilder {

	{
		get("/users", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("other...");
			}
		});
	}
	
}
