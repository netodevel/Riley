package br.com.riley.router;

import br.com.riley.router.reactive.RouterContext;
import io.reactivex.Observable;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Optional;

import static br.com.riley.router.helper.RegexHelper.matchUrl;

@NoArgsConstructor
public class RouteManager {

    private RouteRegistry routeRegistry;

    public RouteManager(RouteRegistry routeRegistry) {
        this.routeRegistry = routeRegistry;
    }

    public Observable<?> executeRequest(String url) {
        Optional<RouterContext> routeReturned = routeRegistry.getRoutes().stream()
                .filter(route -> matchUrl(route.getRegex(), url))
                .map(route -> buildRouterCtx(route))
                .findFirst();

        if (routeReturned.isPresent()) {
            return routeReturned.get().getReactiveRouteHandler().execute(routeReturned.get());
        }
        return Observable.just(new RouterException());
    }

    private RouterContext buildRouterCtx(Route route) {
        return RouterContext.builder()
                .params(new HashMap<>())
                .reactiveRouteHandler(route.getReactiveRouteHandler())
                .build();
    }

}
