package br.com.riley.router;

import br.com.riley.router.reactive.ReactiveRouteAction;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.riley.router.RegexHelper.matchUrl;

public class Router {

    private static Router instance;
    public static List<Route> routes = new ArrayList<>();

    public static synchronized Router getInstance(){
        if (instance == null){
            instance = new Router();
        }
        return instance;
    }

    public Observable<?> executeRequest(String url) {
        Optional<Route> routeReturned = routes.stream()
                .filter(route -> matchUrl(route.getRegex(), url))
                .findFirst();
        if (routeReturned.isPresent()) return routeReturned.get().getReactiveRouteAction().execute();
        return Observable.just(new RouterException());
    }

    public static Route get(String url, ReactiveRouteAction reactiveRouteAction) {
        Route route = Route.builder()
                .path(url)
                .method(HttpConsts.METHOD_GET)
                .regex(RegexHelper.generatorRegexFromUrl(url))
                .reactiveRouteAction(reactiveRouteAction)
                .build();

        routes.add(route); //TODO: definir outro ponto para cadastrar na lista de rotas.
        return route;
    }

}
