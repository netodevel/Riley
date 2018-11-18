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

2018-11-18 12:14:32.103:INFO::main: Logging initialized @589ms to org.eclipse.jetty.util.log.StdErrLog
2018-11-18 12:14:32.514:INFO:oejs.Server:main: jetty-9.4.0.v20161208
2018-11-18 12:14:32.644:INFO:oejs.session:main: DefaultSessionIdManager workerName=node0
2018-11-18 12:14:32.644:INFO:oejs.session:main: No SessionScavenger set, using defaults
2018-11-18 12:14:32.654:INFO:oejs.session:main: Scavenging every 660000ms
2018-11-18 12:14:32.698:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@6aaa5eb0{/,null,AVAILABLE}
2018-11-18 12:14:32.942:INFO:oejs.AbstractConnector:main: Started ServerConnector@662f4769{HTTP/1.1,[http/1.1]}{0.0.0.0:3000}
2018-11-18 12:14:32.943:INFO:oejs.Server:main: Started @1432ms
```
