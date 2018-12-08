package br.com.riley.router;

import io.reactivex.Observable;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static br.com.riley.router.helper.RegexHelper.matchUrl;

@NoArgsConstructor
public class RouteManager {

    private RouteRegistry routeRegistry;

    public RouteManager(RouteRegistry routeRegistry) {
        this.routeRegistry = routeRegistry;
    }

    public Observable<?> executeRequest(String url) {
        Optional<Route> routeReturned = routeRegistry.getRoutes().stream()
                .filter(route -> matchUrl(route.getRegex(), url))
                .findFirst();
        if (routeReturned.isPresent()) return routeReturned.get().getReactiveRouteHandler().execute();
        return Observable.just(new RouterException());
    }

}
