package br.com.rileyframework;

import br.com.rileyframework.utils.GeneratorRegex;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RileyTest {

    private Riley riley;

    @Before
    public void setUp() {
        riley = new Riley();
    }

    @Test
    public void dado_uma_lista_de_rotas_deve_retornar_todas_rotas() {
        Route route = Route.builder()
                .httpMethod("GET")
                .route("/json")
                .handler((request, response) -> response.json("hello world").status(200))
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/json"))
                .build();

        Route routeTwo = Route.builder()
                .httpMethod("GET")
                .route("/home")
                .handler((request, response) -> response.json("home").status(200))
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/home"))
                .build();


        riley.setRoutes(asList(route, routeTwo));
        assertEquals(riley.getRoutes().size(), 2);
    }

    @Test
    public void dado_uma_rota_deve_retornar_a_mesma() {
        Route route = Route.builder()
                .httpMethod("GET")
                .route("/json")
                .handler((request, response) -> response.json("hello world").status(200))
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/json"))
                .build();

        riley.setRoutes(asList(route));
        assertEquals(riley.getRoutes().get(0).getRoute(), "/json");
    }

}
