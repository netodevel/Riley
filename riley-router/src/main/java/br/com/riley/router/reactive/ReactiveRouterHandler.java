package br.com.riley.router.reactive;

import io.reactivex.Observable;

public interface ReactiveRouterHandler<T> {

    Observable<T> execute();
}
