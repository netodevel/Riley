package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import br.com.riley.router.RouterException;
import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class ReactiveRouteManagerTest {{

    describe("dado uma rota reativa", () -> {
        RouteManager.get("/index", ()-> Observable.just("hello world"));

        it("deve retornar hello world", ()-> {
            RouteManager routeManager = RouteManager.getInstance();
            routeManager.routes.get(0).getReactiveRouterHandler()
                    .execute()
                    .subscribe(res -> { assertEquals("hello world", res); });
        });
    });

    describe("dado uma url", () -> {
        String url = "/users";
        context("com duas rotas registradas", () -> {
            RouteManager.get("/index", ()-> Observable.just("hello world"));
            RouteManager.get("/users", ()-> Observable.just("hello users"));

            it("deve executar a rota '/users'", () -> {
                RouteManager routeManager = RouteManager.getInstance();

                routeManager.executeRequest(url).subscribe(res -> {
                    assertEquals("hello users", res);
                });
            });
        });

        context("nao encontrada", ()-> {
            it("deve retornar RouterException", () -> {
                RouteManager routeManager = new RouteManager();
                routeManager.routes = asList();

                routeManager.executeRequest(url).subscribe(res -> {
                    Assert.assertEquals(new RouterException(), res);
                });
            });
        });
    });

}}
