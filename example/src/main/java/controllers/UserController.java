package controllers;

import models.User;
import br.com.rileyframework.ApplicationController;
import br.com.rileyframework.Request;
import br.com.rileyframework.Response;

public class UserController extends ApplicationController {
	
	{
		// GET /users/1
		get("/users/{user_id}", new HttpHandlerRequest() {
			public void handler(Request request, Response response) {
				String idUser = request.param("user_id");
				User user = new User(Integer.parseInt(idUser), "NetoDevel", "josevieira.dev@gmail.com");
				response.json(user);
			}
		});
	}
	
}
