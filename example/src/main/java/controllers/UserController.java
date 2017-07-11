package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.ApplicationController;

public class UserController extends ApplicationController {

	{
		get("/users", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("other...");
			}
		});
		
		get("/users/{user_id}", (request, response) -> {
			System.out.println("show user..");
		});
		
		get("/users/{user_id}/books", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("user to book...");
			}
		});
		
		get("/books/{book_id}/users", new HttpHandlerRequest() {
			public void handler(HttpServletRequest request, HttpServletResponse response) {
				System.out.println("book to users...");
			}
		});
	}
	
}
