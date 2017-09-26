# RileyFramework
The repository purpose is learn how to build micro web framework with Java

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
## Hello World
```java
public class HomeController extends ApplicationController {
	
  {
    get("/home", (req, res) -> {
      return res.json("Hello World").status(200);
    });
  }
	
}

```
