package controllers;

import br.com.rileyframework.ApplicationController;
import commands.users.UserSaveCommand;
import models.User;

public class UserController extends ApplicationController {
	
	{
		get("/users/{user_id}", (request, response) -> {
			String idUser = request.getPathVariables().get("{user_id}");
			
			UserSaveCommand userSaveCommand = new UserSaveCommand();
			
			userSaveCommand.onSuccess(() -> {
				User user = new User();
				user.setId(Integer.parseInt(idUser));
				user.setName("NetoDevel");
				user.setEmail("josevieira.dev@gmail.com");
				response.json(user);
			});
			
			userSaveCommand.onFailed(() -> {
				String error = "Error save user.";
				response.json(error);
			});
			
			userSaveCommand.saveUser(Integer.parseInt(idUser));
		});
	}
	
}
