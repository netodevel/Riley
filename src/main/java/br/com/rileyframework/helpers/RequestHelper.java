package br.com.rileyframework.helpers;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean matchUrl(String regex, String urlOrigin) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(urlOrigin);
        return m.matches();
    }

    public static String getBodyRequest(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        return jb.toString();
    }

}
