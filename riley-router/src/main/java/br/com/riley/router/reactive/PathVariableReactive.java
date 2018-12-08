package br.com.riley.router.reactive;


import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Arrays;
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

}
