package controllers;

import br.com.rileyframework.ApplicationController;
import commands.users.UserSaveCommand;
import models.User;

public class UserController extends ApplicationController {
	
	{
		// GET /users/1
		get("/users/{user_id}", (request, response) -> {
			Integer userId = request.intParam("user_id");
			UserSaveCommand userSaveCommand = new UserSaveCommand();
			
			userSaveCommand.onSuccess(()-> {
				User user = new User(userId, "NetoDevel", "josevieira.dev@gmail.com");
				response.status(200).json(user);
			});
			
			userSaveCommand.onFailed(() -> {
				response.status(404);
			});
			
			userSaveCommand.saveUser(userId);
			return response;
		});
		
		post("/users", (request, response) -> {
			User user = (User) request.body(User.class);
			return response.status(201).json(user);
		});
		
	}
	
}
