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
public class HelloWorldController {{
    get("/index", (ctx) -> Observable.just("hello world"));
    get("/user/{user_id}", (ctx) -> Observable.just(ctx.params.get("{user_id}")));
}}
```
