package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouteRegistry;
import br.com.riley.router.RouterException;
import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class ReactiveRouteManagerTest {{

    describe("dado uma rota reativa", () -> {
        RouteRegistry routeRegistry = new RouteRegistry();

        it("deve retornar hello world", ()-> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/index", (ctx)-> Observable.just("hello world"));
            routeRegistry.getRoutes().get(0).getReactiveRouteHandler()
                    .execute(RouterContext.builder().build())
                    .subscribe(res -> { assertEquals("hello world", res); });
        });
    });

    describe("dado uma url", () -> {
        RouteRegistry routeRegistry = new RouteRegistry();

        String url = "/users";

        context("com duas rotas registradas", () -> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/index", (ctx)-> Observable.just("hello world"));
            routeRegistry.get("/users", (ctx)-> Observable.just("hello world"));

            it("deve executar a rota '/users'", () -> {
                RouteManager routeManager = new RouteManager(routeRegistry);
                routeManager.executeRequest(url).subscribe(res -> {
                    assertEquals("hello users", res);
                });
            });
        });

        context("nao encontrada", ()-> {
            it("deve retornar RouterException", () -> {
                RouteManager routeManager = new RouteManager();

                routeManager.executeRequest(url).subscribe(res -> {
                    Assert.assertEquals(new RouterException(), res);
                });

            });
        });
    });

}}
