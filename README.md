# Riley Framework

## Atenção 
Esse repositório tem como objetivo criar um framework para aprendizado e praticar TDD e Algoritimos.

<b>Aviso</b>: Comecei o projeto totalmente sem testes e sem seguir nenhuma boa prática, estou começando a restruturar o projeto para seguir o objetivo.

### Quick Start

```java
public class RileyApplication {
    public static void main(String[] args) throws Exception {
        new Riley().start();
    }
}
```
#### Hello World

```java
public class RileyApplication {
    public static void main(String[] args) throws Exception {
        Riley.getInstance().start();
        get("/index", (ctx) -> json("Hello World"));
    }
}
```

#### Other way

```java
public class HelloWorldController {{
    get("/index", (ctx) -> json("hello world"));
    get("/user/{user_id}", (ctx) -> json(ctx.params.get("{user_id}")));
}}
```
#### Output
```
____       ____  _ __              
\ \ \     / __ \(_) /__  __  __    
 \ \ \   / /_/ / / / _ \/ / / /    
 / / /  / _, _/ / /  __/ /_/ /     
/_/_/  /_/ |_/_/_/\___/\__, /      
                      /____/       

[INFO] Starting server...
[INFO] Riley Application started in development on http://localhost:3000
[INFO] Registered Route: GET /index
[INFO] Registered Route: GET /user/{user_id}
```
