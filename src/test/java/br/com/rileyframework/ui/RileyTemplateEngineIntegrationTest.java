package br.com.rileyframework.ui;

import br.com.rileyframework.Riley;
import br.com.rileyframework.Route;
import br.com.rileyframework.utils.GeneratorRegex;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RileyTemplateEngineIntegrationTest {

    private static final Riley riley = Riley.getInstance();

    @BeforeClass
    public static void setUp() throws Exception {
        riley.start();
    }

    @Test
    public void dado_uma_rota_html_deve_retornar_um_html_gerado() throws IOException {
        Route routeHtml = Route.builder()
                .route("/index")
                .routeRegex(GeneratorRegex.generatorRegexFromUrl("/index"))
                .httpMethod("GET")
                .type("HTML")
                .handler((req, res) -> {
                    Map<String, Object> modelAndView = new HashMap<>();
                    modelAndView.put("{name}", "Neto");

                    return res.html("Hello, {name}")
                            .modelAndView(modelAndView)
                            .status(200);
                })
                .build();

        riley.registerController(routeHtml);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:3000/index");
        HttpResponse httpResponse = httpClient.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String json = "";
        while ((json = rd.readLine()) != null) {
            result.append(json);
        }

        assertEquals("Hello, Neto", result.toString());
    }

    @AfterClass
    public static void afterDown() throws Exception {
        riley.shutDown();
    }

}
