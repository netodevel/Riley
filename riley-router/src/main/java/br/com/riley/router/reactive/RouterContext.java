package br.com.riley.router.reactive;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class RouterContext {

    public HashMap<String, String> params;
    private ReactiveRouteHandler reactiveRouteHandler;
}
