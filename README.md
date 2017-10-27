# RileyFramework
The repository purpose is learn how to build micro web framework with Java

A simple abstraction of servlet api

Inspired by express and spring


# Architecture

    riley-app
    |--- 
         |--initializers
            Application.java
         |--controllers
            UserController.java

## Quick Start
```java
public class Application {

    public static void main(String[] args) throws Exception {
       new Riley().init(Servers.JETTY);
    }	
}
```
## Hello world
```java
public class HomeController extends ApplicationController {
	
  {
    get("/home", (req, res) -> {
      return res.json("Hello World").status(200);
    });
  }
	
}

```

## Simple controller
```java
public class UserController extends ApplicationController {
	{
		baseUrl("/api/v1");
		
		// GET /users
		get("/users", (request, response) -> {
			UserListInteractor userList = new UserListInteractor(new UserRepository());
			return response.status(200).json(userList.all());
		});
		
		// GET /users/1
		get("/users/{user_id}", (request, response) -> {
			Integer userId = request.intParam("user_id");
			User user = new User(userId, "NetoDevel", "josevieira.dev@gmail.com");
			return response.status(200).json(user);
		});
		
		// POST /users
		post("/users", (request, response) -> {
			User user = (User) request.body(User.class);
			return response.status(201).json(user);
		});
		
		// PUT /users/1
		put("/users/{user_id}", (request, response) -> {
			Integer userId = request.intParam("user_id");
			User user = new User(userId, "netoupdate", "josevieira.dev@gmail.com");
			return response.status(200).json(user);
		});
		
		// DELETE /users/1
		delete("/users/{user_id}", (request, response) -> {
			String success = "success";
			return response.status(200).json(success);
		});
	}
}
```
