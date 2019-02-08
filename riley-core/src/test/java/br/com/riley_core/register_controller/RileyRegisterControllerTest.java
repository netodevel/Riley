package br.com.riley_core.register_controller;

import br.com.riley_core.Riley;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RileyRegisterControllerTest {

    private static final Riley riley = Riley.getInstance();

    @Before
    public void setUp() throws Exception {
        this.riley.start();
    }

    @Test
    public void deveRetornar200() throws IOException {
        this.riley.registerControllers(asList(new TestHelloWorldController()));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:3000/index");
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void deveRetornarHelloWorld() throws IOException {
        this.riley.registerControllers(asList(new TestHelloWorldController()));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:3000/index");
        HttpResponse httpResponse = httpClient.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String json = "";
        while ((json = rd.readLine()) != null) {
            result.append(json);
        }
        assertEquals("Hello World", result.toString());
    }

    @After
    public void tearDown() throws Exception {
        this.riley.shutDown();
    }

}
