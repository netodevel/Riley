package br.com.riley.router;

import br.com.riley.router.reactive.PathVariableReactive;
import br.com.riley.router.reactive.RouterContext;
import io.reactivex.Observable;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Optional;

import static br.com.riley.router.helper.RegexHelper.matchUrl;

@NoArgsConstructor
public class RouteManager {

    private RouteRegistry routeRegistry;
    private PathVariableReactive pathVariableReactive = new PathVariableReactive();

    public RouteManager(RouteRegistry routeRegistry) {
        this.routeRegistry = routeRegistry;
    }

    public Observable<?> executeRequest(String url) {
        Optional<RouterContext> routeReturned = routeRegistry.getRoutes().stream()
                .filter(route -> matchUrl(route.getRegex(), url))
                .map(route -> buildRouterCtx(route, url))
                .findFirst();

        if (routeReturned.isPresent()) {
            return routeReturned.get().getReactiveRouteHandler().execute(routeReturned.get());
        }
        return Observable.just(new RouterException());
    }

    private RouterContext buildRouterCtx(Route route, String url) {
        HashMap<String, String> paramsMap = pathVariableReactive.param(route.getPath(), url).blockingFirst();

        return RouterContext.builder()
                .params(paramsMap)
                .reactiveRouteHandler(route.getReactiveRouteHandler())
                .build();
    }

}
