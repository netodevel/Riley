package br.com.riley.router;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Router {

    protected List<Route> routes = new ArrayList<>();

    public Observable<?> executeRequest(String url) {
        Optional<Route> routeReturned = routes.stream()
                .filter(route -> route.getPath().equals(url))
                .findFirst();
        if (routeReturned.isPresent()) return routeReturned.get().getReactiveRouteAction().execute();
        return Observable.just(new RouterException());
    }

    public static void get(String url, ReactiveRouteAction reactiveRouteAction) {

    }

}
