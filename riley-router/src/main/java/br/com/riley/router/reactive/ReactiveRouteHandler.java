package br.com.riley.router.reactive;

import io.reactivex.Observable;

public interface ReactiveRouteHandler<T> {

    Observable<T> execute(RouterContext routerContext);
}
