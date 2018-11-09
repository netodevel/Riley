package br.com.rileyframework.ui;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RileyTemplateEngineTest {

    @Test
    public void dado_um_html_com_hvars_deve_retornar_html_gerado() {
        String htmlPuro = "Hello, {var}";

        HashMap<String, Object> viewAttributes = new HashMap<>();
        viewAttributes.put("{var}", "Neto");

        String htmlResult = new RileyTemplateEngine()
                .modelAndView(viewAttributes)
                .format(htmlPuro);

        assertEquals("Hello, Neto", htmlResult);
    }

    @Test
    public void dado_um_html_deve_retornar_todas_hvar() {
        String html = " <h1>Hello {{world}} </h1>";
        List<String> hVars = new RileyTemplateEngine().hVars(html);
        assertEquals(1, hVars.size());
    }

    @Test
    public void dado_uma_lista_de_hvars_deve_retornar_um_map_com_valores() {
        List<String> hVars = Arrays.asList("world");

        Map<String, Object> modelAndView = new HashMap<>();
        modelAndView.put("world", "World");

        HashMap<String, Object> hValues = new RileyTemplateEngine()
                .modelAndView(modelAndView)
                .hVarToValue(hVars);
        assertEquals(hValues.get("world"), "World");
    }

}
