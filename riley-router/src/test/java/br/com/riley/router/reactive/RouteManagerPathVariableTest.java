package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouteRegistry;
import br.com.riley.router.RouterException;
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

        this.routeRegistry.clearRoutes();
    }

    @Test
    public void dadoUmaRotaComUserId_deveRetornarUmParametro() {
        get("/user/{user_id}", (ctx) -> {
            String userId = ctx.params.get("{user_id}");

            if (userId == null) return Observable.just(new RouterException());
            return Observable.just(userId);
        });
        String url = "/user/1";

        routeManager.executeRequest(url).subscribe( r -> {
            System.out.println(r);
        });

        //assertEquals(1, routeRegistry.routes.size());
    }

    @Test
    public void dadoUmaRotaComUserId_e_commentId_deveRetornarOsParametros() {
        get("/user/{user_id}/comments/{comment_id}", (ctx) -> {
            String userId = ctx.params.get("{user_id}");
            String commentId = ctx.params.get("{comment_id}");

            return Observable.just(userId, commentId);
        });

        String url = "/user/1/comments/1040";

        routeManager.executeRequest(url).subscribe( r -> {
            System.out.println(r);
        });
    }

}
