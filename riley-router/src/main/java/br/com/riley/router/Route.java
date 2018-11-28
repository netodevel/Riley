package br.com.riley.router;

import br.com.riley.router.reactive.ReactiveRouteAction;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Route {

    private String method;
    private String path;
    private String regex;
    private RouteAction routeAction;
    private ReactiveRouteAction reactiveRouteAction;
}
