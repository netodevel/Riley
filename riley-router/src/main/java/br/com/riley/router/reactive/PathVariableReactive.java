package br.com.riley.router.reactive;


import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PathVariableReactive {

    public Observable<List<String>> getParams(String url) {
        String[] urlSplit = url.split("/");

        List<String> params = Arrays.stream(urlSplit)
                .filter(param -> isParameter(param).blockingGet())
                .collect(Collectors.toList());

        return Observable.just(params);
    }

    public Single<Boolean> isParameter(String url) {
        if (url.startsWith("{") && url.endsWith("}")) return Single.just(true);
        return Single.just(false);
    }

    public Observable<HashMap<String, String>> param(String url, String contextPath) {
        return getParams(url)
                .map(params -> toMap(params, contextPath));
    }

    private HashMap<String, String> toMap(List<String> params, String contextPath) {
        HashMap<String, String> pathVariables = new HashMap<>();
        String[] valuesFromContextPath = contextPath.split("\\/\\w*\\/");

        for (int i = 0; i < params.size(); i++) {
            pathVariables.put(params.get(i), valuesFromContextPath[i + 1]);
        }

        return pathVariables;
    }

}
