package br.com.riley.router;


import io.reactivex.Observable;

import static br.com.riley.router.RouteManager.get;

public class SampleRouter {{

    get("/index", () -> {
        return Observable.just("");
    });

}}
