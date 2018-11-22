package br.com.riley.router;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Route {

    private String method;
    private String path;
    private RouteAction routeAction;
}
