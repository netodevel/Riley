package br.com.riley.router;


import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.greghaskins.spectrum.Spectrum.*;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class RouteRegistryTest {{

    describe("dado uma rota GET", ()-> {
        RouteRegistry routeRegistry = new RouteRegistry();

        afterEach(routeRegistry.getRoutes()::clear);

        it("deve inserir na lista", ()-> {
            routeRegistry.get("", () -> Observable.just("hello world"));
            assertEquals(1, routeRegistry.getRoutes().size());
        });

        it("deve retornar a url", () -> {
            routeRegistry.get("/index", ()-> Observable.just("index"));
            assertEquals("/index", routeRegistry.getRoutes().get(0).getPath());
        });

        it("deve retornar o regex", () -> {
            routeRegistry.get("/user/{user_id}", ()-> Observable.just("findUser"));
            assertEquals("/user/\\w*", routeRegistry.getRoutes().get(0).getRegex());
        });

        it("deve retornar o metodo", () -> {
            routeRegistry.get("/user/{user_id}", ()-> Observable.just("findUser"));
            assertEquals("GET", routeRegistry.getRoutes().get(0).getMethod());
        });

        it("deve retornar a string", () -> {
            routeRegistry.get("/user/{user_id}", ()-> Observable.just("return"));


            Observable<String> response = routeRegistry.getRoutes().get(0).getReactiveRouterHandler().execute();

            AtomicBoolean started = new AtomicBoolean(true);

            response.subscribe(res -> {
                assertEquals("return", res);
                started.set(false);
            });

            while (started.get()) {}
        });

    });

}}
