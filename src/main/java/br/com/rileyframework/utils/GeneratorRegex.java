package br.com.rileyframework.utils;

/**
 * @author NetoDevel
 */
public class GeneratorRegex {

	/**
	 * Parse URL mapped for Regex
	 *
	 * @param  urlMapped
	 * @author NetoDevel
	 * @return Regex from string
	 */
	public static String generatorRegexFromUrl(String urlMapped) {
		return urlMapped.replaceAll("\\{\\w*\\}", "\\\\w*");
	}
	
}
