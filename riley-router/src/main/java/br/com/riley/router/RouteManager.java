package br.com.riley.router;

import br.com.riley.router.reactive.ReactiveRouterHandler;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.riley.router.RegexHelper.matchUrl;

public class RouteManager {

    private static RouteManager instance;
    public static List<Route> routes = new ArrayList<>();

    public static synchronized RouteManager getInstance(){
        if (instance == null){
            instance = new RouteManager();
        }
        return instance;
    }

    public Observable<?> executeRequest(String url) {
        Optional<Route> routeReturned = routes.stream()
                .filter(route -> matchUrl(route.getRegex(), url))
                .findFirst();
        if (routeReturned.isPresent()) return routeReturned.get().getReactiveRouterHandler().execute();
        return Observable.just(new RouterException());
    }

    public static Route get(String url, ReactiveRouterHandler reactiveRouterHandler) {
        Route route = Route.builder()
                .path(url)
                .method(HttpConsts.METHOD_GET)
                .regex(RegexHelper.generatorRegexFromUrl(url))
                .reactiveRouterHandler(reactiveRouterHandler)
                .build();

        routes.add(route); //TODO: definir outro ponto para cadastrar na lista de rotas.
        return route;
    }

}
