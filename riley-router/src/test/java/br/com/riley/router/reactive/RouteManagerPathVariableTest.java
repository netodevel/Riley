package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouteRegistry;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import static br.com.riley.router.RouteRegistry.get;


public class RouteManagerPathVariableTest {

    private RouteManager routeManager;
    private RouteRegistry routeRegistry;

    @Before
    public void setUp() {
        this.routeRegistry = new RouteRegistry();
        this.routeManager = new RouteManager(routeRegistry);
    }

    @Test
    public void dadoUmaRotaComUserId_deveRetornarUmParametro() {
        get("/user/{user_id}", (ctx) -> Observable.just("hello"));
        String url = "/user/1";
    }

}
