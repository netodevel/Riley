package br.com.rileyframework.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RileyTemplateEngine {

    private static final String REGEX_PATTERN_HVAR = ("(\\{(\\w+)\\})+");
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

    public List<String> hVars(String html) {
        List<String> hVarsResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_PATTERN_HVAR);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            hVarsResult.add(matcher.group());
        }
        return hVarsResult;
    }
}
