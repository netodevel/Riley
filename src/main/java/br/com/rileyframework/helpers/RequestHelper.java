package br.com.rileyframework.helpers;

import java.util.HashMap;

public class RequestHelper {

    public static String validateUrlToRegex(String url) {
        String firstCaracter = String.valueOf(url.charAt(0));
        if (!firstCaracter.equals("/")) {
            url = "/" + url;
        }
        return url;
    }

    public static HashMap<String, String> getPathVariables(String url, String contextPath) {
        url = validateUrlToRegex(url);

        String[] paramName = url.split("\\/\\w*\\/");
        String[] paramValue = contextPath.split("\\/\\w*\\/");
        HashMap<String, String> pathVariables = new HashMap<>();

        for (int i = 0; i < paramValue.length; i++) {
            if (paramValue[i].equals("") || paramValue[i].equals(null)) {
                continue;
            } else {
                pathVariables.put(paramName[i], paramValue[i]);
            }
        }
        return pathVariables;
    }

}
