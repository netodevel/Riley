package br.com.riley.router;

import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.runner.RunWith;

import java.util.List;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class ReactiveRouterTest {{

    describe("dado uma rota reativa", () -> {
        Route routeReact = Route.builder()
                .method(HttpConsts.METHOD_GET)
                .path("/index")
                .reactiveRouteAction(() -> Observable.just("hello world"))
                .build();

        List<Route> routes = asList(routeReact);

        it("deve retornar hello world", ()-> {
            routes.get(0).getReactiveRouteAction()
                    .execute()
                    .subscribe(res -> { assertEquals("hello world", res); });
        });
    });

    describe("dado uma url", () -> {
        String url = "/users";
        context("com duas rotas registradas", () -> {
            Route routeIndex = Route.builder().method(HttpConsts.METHOD_GET)
                    .path("/index").reactiveRouteAction(() -> Observable.just("hello world"))
                    .build();

            Route routeUsers = Route.builder().method(HttpConsts.METHOD_GET)
                    .path("/users").reactiveRouteAction(() -> Observable.just("hello users"))
                    .build();

            List<Route> routes = asList(routeIndex, routeUsers);

            it("deve executar a rota '/users'", () -> {
                Router router = new Router();
                router.routes = routes;

                router.executeRequest(url).subscribe(res -> {
                    assertEquals("hello users", res);
                });
            });
        });

        context("nao encontrada", ()-> {
            it("deve retornar RouterException", () -> {
                Router router = new Router();
                router.routes = asList();

                router.executeRequest(url).subscribe(res -> {
                    assertEquals(new RouterException(), res);
                });
            });
        });

    });

}}
