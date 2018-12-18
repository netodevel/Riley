package br.com.riley.router;


import br.com.riley.router.reactive.RouterContext;
import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class RouteRegistryTest {{

    describe("dado uma rota GET", ()-> {
        RouteRegistry routeRegistry = new RouteRegistry();

        it("deve inserir na lista", ()-> {
            routeRegistry.clearRoutes();
            routeRegistry.get("", (router) -> Observable.just("hello world"));
            assertEquals(1, routeRegistry.routes.size());
        });

        it("deve retornar a url", () -> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/index", (router)-> Observable.just("index"));
            assertEquals("/index", routeRegistry.routes.get(0).getPath());
        });

        it("deve retornar o regex", () -> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/user/{user_id}", (router)-> Observable.just("findUser"));
            assertEquals("/user/\\w*", routeRegistry.routes.get(0).getRegex());
        });

        it("deve retornar o metodo", () -> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/user/{user_id}", (router)-> Observable.just("findUser"));
            assertEquals("GET", routeRegistry.routes.get(0).getMethod());
        });

        it("deve retornar a string", () -> {
            routeRegistry.clearRoutes();
            routeRegistry.get("/user/{user_id}", (router)-> Observable.just("return"));

            Observable<String> response = routeRegistry.routes.get(0).getReactiveRouteHandler().execute(RouterContext.builder().build());

            response.subscribe(res -> {
                assertEquals("return", res);
            });
        });
    });

}}
