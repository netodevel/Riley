package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouteRegistry;
import br.com.riley.router.RouterException;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import static br.com.riley.router.RouteRegistry.get;
import static org.junit.Assert.assertEquals;

public class RouteManagerPathVariableTest {

    private RouteManager routeManager;
    private RouteRegistry routeRegistry;

    @Before
    public void setUp() {
        this.routeRegistry = new RouteRegistry();
        this.routeManager = new RouteManager(routeRegistry);

        this.routeRegistry.clearRoutes();
    }

    @Test
    public void dadoUmaRotaComUserId_deveRetornarUmParametro() {
        get("/user/{user_id}", (ctx) -> {
            String userId = ctx.getParams().get("{user_id}");
            if (userId == null) return Observable.just(new RouterException());
            return Observable.just(userId);
        });
        String url = "/user/1";

        routeManager.executeRequest(url).subscribe( r -> {
            assertEquals("{user_id}", r);
        });

        assertEquals(1, routeRegistry.routes.size());
    }

}
