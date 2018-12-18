package br.com.riley.router;

import io.reactivex.Observable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesListTest {

    @Test
    public void deveRetornarVazioPorPadrão() {
        RouteRegistry routeRegistry = new RouteRegistry();
        routeRegistry.clearRoutes();
        assertEquals(0, routeRegistry.routes.size());
    }

    @Test
    public void deveRetornarUmaRotaRegistrada() {
        RouteRegistry routeRegistry = new RouteRegistry();
        routeRegistry.clearRoutes();
        routeRegistry.get("", (ctx)-> Observable.just("hello"));
        assertEquals(1, routeRegistry.routes.size());
    }

    @Test
    public void deveRetornarVazioPorInstancia() {
        RouteRegistry routeRegistry = new RouteRegistry();
        routeRegistry.clearRoutes();
        assertEquals(0, routeRegistry.routes.size());
    }

}
