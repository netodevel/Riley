package br.com.rileyframework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.rileyframework.HandlerMapping;

public class PathVariablesUtil {

	public String getActionURL(String uri, List<HandlerMapping> handlerMapping) {
		String requestURL = uri;
		String[] paths = requestURL.split("/");
		
		List<String> requestsMapped = new ArrayList<String>();
		for (HandlerMapping handlerMap : handlerMapping) {
			requestsMapped.add(handlerMap.getAction());
		}
		
		for (String requests : requestsMapped) {
			String [] pathsURI = requests.split("/");
			if (pathsURI.length != paths.length) {
				continue;
			} 
			List<String> mappingStatic = new ArrayList<String>();
			List<String> mappingVariable = new ArrayList<String>();
			for (String string : pathsURI) {
				if (!string.contains("{")) {
					mappingStatic.add(string);
				} else {
					mappingVariable.add(string);
				}
			}
			
			List<Integer> indiceMaisProximo = new ArrayList<Integer>();
			for (String pathOrigin : paths) {
				if (pathOrigin.equals("")) {
					continue;
				}
				if (mappingStatic.contains(pathOrigin)) {
					indiceMaisProximo.add(mappingStatic.indexOf(pathOrigin));
				}
			}
			
			for (int j = 0; j < paths.length; j++) {
				if (paths[j].equals("")) {
					continue;
				}
				if (indiceMaisProximo.size() > 0 && indiceMaisProximo.get(0) == 1) {
					if (paths[j].equals(mappingStatic.get(indiceMaisProximo.get(0)))) {
						return requests;
					}
				}
			}
			
		}
		
		return null;
	}
	
	public List<String> getParameters(String url, String actionURL) {
		HashMap<String, String> params = new HashMap<String, String>();
		List<String> returnParams = new ArrayList<String>();
		List<Integer> indiceParams = new ArrayList<Integer>();
		
		String[] actionUrlSplit = actionURL.split("/");
		String [] urlOrigin = url.split("/");

		for (int i = 0; i < actionUrlSplit.length; i++) {
			if (actionUrlSplit[i].equals("")) {
				continue;
			}
			if (actionUrlSplit[i].contains("{")) {
				indiceParams.add(i);
				params.put(actionUrlSplit[i], urlOrigin[i]);
			}
		}
		
		for (String key : params.keySet()) {
			returnParams.add(params.get(key));
		}
		return returnParams;
	}
	
}
