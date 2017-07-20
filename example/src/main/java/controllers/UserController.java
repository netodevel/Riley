package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.rileyframework.ApplicationController;
import br.com.rileyframework.Request;
import iteractors.UserIteractor;
import models.User;

public class UserController extends ApplicationController {
	
	{
		get("/users/{user_id}", new HttpHandlerRequest() {
			
			@Override
			public void handler(Request request, HttpServletResponse response) {
				String userId = request.getPathVariables().get("{user_id}");

				UserIteractor userIteractor = new UserIteractor();
				User userResponse = userIteractor.find(Integer.parseInt(userId));

				try {
					response.getWriter().print(json(userResponse));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
