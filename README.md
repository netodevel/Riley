# RileyFramework
The repository purpose is learn how to build micro web framework with Java

# Architecture
Riley is based on principle of Clean Architecture


    riley-app
    |--- web ()
         |--intializers
            Application.java
         |--controllers
            UserController.java

    |--- domain ()
         |--interactors
            |--users
               GetUserListInteractor.java

    |--- infrastructure ()
         |--dao
            UserDaoBoundary.java
            UserDaoImpl.java
          
         

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
