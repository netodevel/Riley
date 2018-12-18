package br.com.riley.router;

import br.com.riley.router.helper.RegexHelper;
import br.com.riley.router.reactive.ReactiveRouteHandler;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por registrar rotas
 */
@Data
public class RouteRegistry {

    @Getter
    public static List<Route> routes = new ArrayList<>();

    /**
     * Metodo responsável por registrar uma rota GET
     * @param url
     * @param routerHandler
     */
    public static void get(String url, ReactiveRouteHandler routerHandler) {
        Route route = Route.builder()
                .regex(RegexHelper.generatorRegexFromUrl(url))
                .path(url)
                .reactiveRouteHandler(routerHandler)
                .method(HttpConsts.METHOD_GET)
                .build();

        routes.add(route);
    }

    public static void clearRoutes() {
        routes.clear();
    }

}
