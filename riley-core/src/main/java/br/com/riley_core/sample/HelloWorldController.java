package br.com.riley_core.sample;

import io.reactivex.Observable;

import static br.com.riley.router.RouteRegistry.get;

public class HelloWorldController {{

    get("/index", (ctx) -> Observable.just("hello world"));
    get("/user/{user_id}", (ctx) -> Observable.just(ctx.params.get("{user_id}")));
}}
