# RileyFramework
The repository purpose is learn how to build micro web framework with Java

A simple abstraction of servlet api

Inspired by express and spring

# Architecture
    riley-app
    |--- 
         |-- initializers
            Application.java
         |-- http
            UserResource.java

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
public class UserResource extends Resource {

  {
    get("/", (req, res) -> {
      return res.json("HelloWorld").status(200);
    });
  }

}
```

## Register route

```java
public class UserController extends ApplicationController {
  
  {
    baseUrl("/api/v1");

    // GET /users
    get("/users", (request, response) -> {
    });

    // GET /users/1
    get("/users/{user_id}", (request, response) -> {
    });

    // POST /users
    post("/users", (request, response) -> {
    });

    // PUT /users/1
    put("/users/{user_id}", (request, response) -> {
    });

    // DELETE /users/1
    delete("/users/{user_id}", (request, response) -> {
    });
  }
  
}
```

## Request params

### body 
```java
public class UserResource extends Resource {
	
	{
		/POST /users
		post("/users", (request, response) -> {
			User user = (User) request.body(User.class);
			return response.status(201).json(user);
		});
	}
}

```

### intParam

```java
public class UserResource extends Resource {
	
  {
    //GET /users/1
    get("/users/{user_id}", (request, response) -> {
      Integer userId = request.intParam("user_id");
      User user = new User(userId, "NetoDevel", "josevieira.dev@gmail.com");
      return response.status(200).json(user);
    });
  }

}

```
### getParam

```java
public class UserResource extends Resource {

  {
    //GET /users/1
    get("/users/{name}", (request, response) -> {
      String name = request.getParam("name");
      User user = new User(1, name, "josevieira.dev@gmail.com");
      return response.status(200).json(user);
    });
  }

}
```

