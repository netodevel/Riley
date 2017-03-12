package br.com.rileyframework.utils;

/**
 * @author NetoDevel
 */
public class GeneratorRegex {

	/**
	 * Parse URL mapped for Regex
	 * @param urlMapped
	 * @author NetoDevel
	 * @return regex from string
	 */
	public static String generatorRegexFromUrl(String urlMapped) {
		String regex = urlMapped.replaceAll("\\{\\w*\\}", "\\\\w*");
		return regex;
	}
	
}
