package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.ApplicationController;

public class HomeController extends ApplicationController {
	
	{
		get("/home", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				
			}
		});
		
		get("/index2", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("index2...");
			}
		});
	}
}
