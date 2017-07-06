# RileyFramework
The purpose of this repository is to learn how to build a micro web framework in java.

Inspired by Rails, let's try to build a framework similar to this.

## future fetaures

### Controller Rest

```java
public class UserController extends ApplicationController {

  {
    baseUrl("/api/v1/users")
    
    beforeAction(req -> {
    	//TODO
    })

    get("/:id", req -> {
        int id = req.param("id").intValue();
        User user = User.find(id);
        if (user == null) {
          throw new Err(Status.NOT_FOUND);
        }
        return user;
      })
      
     post(req -> {
        User user = req.body().to(User.class);
	
	UserSaveCommand userSaveCommand = new UserSaveCommand();
	userSaveCommand.onSuccess(() -> {
	  System.out.println("success");
	});

	userSaveCommand.onFailed(() -> {
	  System.out.println("error");
	});

	userSaveCommand.save(value);
        
        return user;
     })
  }

}
```

### Model

```java
public class User extends Model {
  static {
  	validatePresence({"name", "email"})
  }
}
```


### Structure

     com.example
         SampleApplication.java
     com.example.controller
         UserController.java
     com.example.model
         User.java

     src/main/resources
          setup.conf


## Usage

### Run
```java
import br.com.rileyframework.RileyFramework;

public class SampleApplication {

	public static void main(String[] args) {
		new RileyFramework().init(SampleApplication.class, Servers.JETTY);
	}
}
```

### RestController
```java
import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.generics.JsonReturn;

@Rest
public class UserController {

	@Get("/users")
	public String index() {
		List<User> users = new ArrayList<User>();
		users.add(new User("1", "josevieira.dev@gmail.com", "password"));
		users.add(new User("2", "netodevel@gmail.com", "password"));
		return JsonReturn.toJson(users);
	}
  
}
```

### Access
    http://localhost:8080/users
```json
[
	{
		"id": "1",
		"mail": "josevieira.dev@gmail.com",
		"password": "password"
	},
	{
		"id": "2",
		"mail": "netodevel@gmail.com",
		"password": "password"
	}
]
```
