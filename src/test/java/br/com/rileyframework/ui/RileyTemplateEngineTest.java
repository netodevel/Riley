package br.com.rileyframework.ui;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RileyTemplateEngineTest {

    @Test
    public void dado_uma_hvar_deve_retornar_string_html() {
        String hvar = "Hello, ${var}";

        HashMap<String, Object> viewAttributes = new HashMap<>();
        viewAttributes.put("var", "Neto");

        String htmlResult = new RileyTemplateEngine()
                .modelAndView(viewAttributes)
                .format(hvar);

        assertEquals("Hello, Neto", htmlResult);
    }

}
