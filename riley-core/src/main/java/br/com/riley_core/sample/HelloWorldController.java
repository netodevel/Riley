package br.com.riley_core.sample;

import static br.com.riley.router.RouteRegistry.get;
import static br.com.riley.router.RouteResponse.json;

public class HelloWorldController {{
        get("/index", (ctx) -> json("hello world"));

        get("/user/{user_id}", (ctx) -> json(ctx.params.get("{user_id}")));

        get("/user/{user_id}/comments/{comments_id}", (ctx) -> {
            String userId = ctx.params.get("{user_id}");
            String commentsId = ctx.params.get("{comments_id}");

            return json("user_id: ".concat(userId).concat(" comments_id: ").concat(commentsId));
        });
}}
