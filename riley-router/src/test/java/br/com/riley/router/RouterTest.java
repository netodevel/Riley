package br.com.riley.router;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void dadoUmaRota_quandoCadastrada_deveRetonarListaDeRotas() {
        Route route = Route.builder()
                .method(HttpConsts.METHOD_GET)
                .path("/home")
                .routeAction(() -> "action executed")
                .build();

        Router router = new Router();
        router.routes.add(route);

        assertEquals(1, router.routes.size());
    }

}
