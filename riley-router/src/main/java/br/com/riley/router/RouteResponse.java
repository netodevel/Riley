package br.com.riley.router;

import io.reactivex.Observable;

public class RouteResponse {

    public static Observable json(Object object) {
        return Observable.just(object);
    }

}
