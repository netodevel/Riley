package br.com.rileyframework.ui;

import java.util.HashMap;

public class RileyTemplateEngine {

    private HashMap<String, Object> viewAttributes;

    public String format(String row) {
        String lineValue = "Hello, ";
        String hvar = "var";
        return lineValue.concat((String) viewAttributes.get(hvar));
    }

    public RileyTemplateEngine modelAndView(HashMap<String, Object> viewAttributes) {
        this.viewAttributes = viewAttributes;
        return this;
    }

}
