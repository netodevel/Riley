package br.com.riley_core.register_controller;

import static br.com.riley.router.RouteRegistry.get;
import static br.com.riley.router.RouteResponse.json;

public class TestHelloWorldController {{

    get("/index", (ctx) -> json("Hello World"));
}}
