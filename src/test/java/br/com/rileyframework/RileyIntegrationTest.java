package br.com.rileyframework;

import br.com.rileyframework.utils.GeneratorRegex;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RileyIntegrationTest {

    private static final Riley riley = Riley.getInstance();

    @Before
    public void setUp() throws Exception {
        riley.start();
    }

    @Test
    public void dado_uma_rota_rest_deve_retornar_status_200() throws Exception {
        Route route = Route.builder()
                .httpMethod("GET")
                .route("/json")
                .handler((request, response) -> response.json("hello world").status(200))
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/json"))
                .build();

        riley.setRoutes(asList(route));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:3000/json");
        HttpResponse httpResponse = httpClient.execute(request);

        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void dado_uma_rota_rest_deve_retonar_um_json() throws Exception {
        Route route = Route.builder()
                .httpMethod("GET")
                .route("/json")
                .handler((request, response) -> response.json("hello world").status(200))
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/json"))
                .build();

        riley.setRoutes(asList(route));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:3000/json");
        HttpResponse httpResponse = httpClient.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String json = "";
        while ((json = rd.readLine()) != null) {
            result.append(json);
        }

        assertEquals("\"hello world\"", result.toString());
    }

    @After
    public void tearDown() throws Exception {
        riley.shutDown();
    }

}
