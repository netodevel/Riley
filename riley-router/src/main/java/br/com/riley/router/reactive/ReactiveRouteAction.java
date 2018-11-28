package br.com.riley.router.reactive;

import io.reactivex.Observable;

public interface ReactiveRouteAction<T> {

    Observable<T> execute();
}
