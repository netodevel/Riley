package br.com.riley.router;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

    /**
     * Converte a url da sua rota para regex.
     *
     * @param  urlMapped
     * @return Regex from string
     */
    public static String generatorRegexFromUrl(String urlMapped) {
        return urlMapped.replaceAll("\\{\\w*\\}", "\\\\w*");
    }

    /**
     * Compara regex da url mapeada com a url requisitada.
     * @param regex
     * @param urlOrigin
     * @return
     */
    public static boolean matchUrl(String regex, String urlOrigin) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(urlOrigin);
        return m.matches();
    }
}
