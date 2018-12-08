package br.com.riley.router;

import br.com.riley.router.blocker.RouteHandler;
import br.com.riley.router.reactive.ReactiveRouteHandler;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Route {

    private String method;
    private String path;
    private String regex;
    private RouteHandler routeHandler;
    private ReactiveRouteHandler reactiveRouteHandler;
}
