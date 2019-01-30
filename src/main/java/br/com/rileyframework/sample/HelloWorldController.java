package br.com.rileyframework.sample;

import io.reactivex.Observable;

import static br.com.riley.router.RouteRegistry.get;

public class HelloWorldController {{

    get("/index", (ctx) -> Observable.just("hello world"));
}}
