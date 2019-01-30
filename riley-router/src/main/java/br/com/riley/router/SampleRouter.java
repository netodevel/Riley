package br.com.riley.router;

import io.reactivex.Observable;

import static br.com.riley.router.RouteRegistry.get;

public class SampleRouter {{

    get("/index", (ctx) -> Observable.just("Hello World"));
    get("/users/{user_id}", (ctx) -> Observable.just(ctx.params.get("{user_id}")));

}}
