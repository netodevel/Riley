package br.com.riley.router;

import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.greghaskins.spectrum.Spectrum.afterEach;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class RouteManagerTest {{

    describe("Dado uma lista de rotas", () -> {
        RouteManager routeManager = RouteManager.getInstance();

        List<Route> routes = new ArrayList<>();
        afterEach(routes::clear);

        it("deve ser vazia por padrao", () -> {
            assertEquals(0, routes.size());
        });

        it("deve cadastrar rotas", () -> {
            Route route = Route.builder()
                    .method(HttpConsts.METHOD_GET)
                    .path("/home")
                    .routeAction(() -> "action executed")
                    .build();

            routeManager.routes.add(route);

            assertEquals(1, routeManager.routes.size());
        });
    });

    describe("dado uma rota get", () -> {
        List<Route> routes = new ArrayList<>();
        Route route = Route.builder()
                .method(HttpConsts.METHOD_GET)
                .path("/")
                .routeAction(() -> "hello world")
                .build();

        routes.add(route);

        it("deve retornar o metodo get", ()-> {
            assertEquals(HttpConsts.METHOD_GET, routes.get(0).getMethod());
        });

        it("deve retornar hello world", ()-> {
            assertEquals("hello world", routes.get(0).getRouteAction().execute());
        });
    });

    describe("Ao registrar uma rota get", ()-> {
        Route routeMapped = RouteManager.get("/users/{user_id}", () -> Observable.just("hello world"));

        context("Com todos parametros validos", ()-> {
            it("deve gerar o regex da rota", ()-> {
                assertEquals("/users/\\w*", routeMapped.getRegex());
            });
        });

        context("com url null", ()-> {
            it("deve lancar uma RouterException", ()-> {
            });
        });

    });

}}


