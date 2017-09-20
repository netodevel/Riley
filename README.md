# RileyFramework
The repository purpose is learn how to build micro web framework with Java

# Architecture

    riley-app
    |--- 
         |--intializers
            Application.java
         |--controllers
            UserController.java

## Quick Start

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
