# Riley Server

### Exemplo de como executar uma aplicação Riley

Com servidor default

```java
public class RileyServerDefault {

    public static void main(String[] args) throws Exception {
        new Riley().start();
    }
}
```

Com servidor customizado

```java
public class RileyServerCustom {

   public static void main(String[] args) throws Exception {
      Riley riley = Riley.getInstance();
      riley.configureServer(TomcatCustom.builder().build());
      riley.start();
   }
}
```


