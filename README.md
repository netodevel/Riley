# Riley Framework

## Atenção 
Esse repositório tem como objetivo criar um framework para aprendizado e praticar TDD e Algoritimos.

<b>Aviso</b>: Comecei o projeto totalmente sem testes e sem seguir nenhuma boa prática, estou começando a restruturar o projeto para seguir o objetivo.

No futuro o projeto tem que ser separado em módulos:

* riley-core
* riley-server
* riley-ui
* riley-orm
* riley-web


## Começando

```java
public class RileyApplication {
    public static void main(String[] args) throws Exception {
        new Riley().start();
    }
}

```
Saída:

```
____       ____  _ __              
\ \ \     / __ \(_) /__  __  __    
 \ \ \   / /_/ / / / _ \/ / / /    
 / / /  / _, _/ / /  __/ /_/ /     
/_/_/  /_/ |_/_/_/\___/\__, /      
                      /____/       

Starting server...
Riley Application started in development on http://localhost:3000
```
