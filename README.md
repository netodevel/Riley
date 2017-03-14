# RileyFramework
For study purposes.

###Structure

     com.example
         SampleApplication.java
     com.example.controller
         UserController.java
     com.example.model
         User.java

     src/main/resources
          setup.conf


##Usage

###Run
```java
import br.com.rileyframework.RileyFramework;

public class SampleApplication {

	public static void main(String[] args) {
		new RileyFramework().init(SampleApplication.class, Servers.JETTY);
	}
}
```

###RestController
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

###Access
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
