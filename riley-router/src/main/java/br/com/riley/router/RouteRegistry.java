package br.com.riley.router;

import br.com.riley.router.reactive.ReactiveRouterHandler;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteRegistry {

    @Getter
    public static List<Route> routes = new ArrayList<>();

    public static void get(String url, ReactiveRouterHandler routerHandler) {
        Route route = Route.builder()
                .regex(RegexHelper.generatorRegexFromUrl(url))
                .path(url)
                .reactiveRouterHandler(routerHandler)
                .method(HttpConsts.METHOD_GET)
                .build();

        routes.add(route);
    }

}
