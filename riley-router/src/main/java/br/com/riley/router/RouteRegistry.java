package br.com.riley.router;

import br.com.riley.router.reactive.ReactiveRouterHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteRegistry {

    private List<Route> routes = new ArrayList<>();

    public void get(String url, ReactiveRouterHandler routerHandler) {
        Route route = Route.builder()
                .regex(RegexHelper.generatorRegexFromUrl(url))
                .path(url)
                .reactiveRouterHandler(routerHandler)
                .method(HttpConsts.METHOD_GET)
                .build();

        this.routes.add(route);
    }

}
