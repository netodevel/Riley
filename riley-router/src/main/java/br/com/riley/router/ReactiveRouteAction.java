package br.com.riley.router;

import io.reactivex.Observable;

public interface ReactiveRouteAction<T> {

    Observable<T> execute();
}
