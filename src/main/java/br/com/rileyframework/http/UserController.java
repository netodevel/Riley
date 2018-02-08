package br.com.rileyframework.http;

import br.com.rileyframework.Resource;

public class UserController extends Resource {

    {
        get("/users", (req, res) -> {
            Thread.sleep(3000);
            return res.json("3 segundos").status(200);
        });

        get("/users2", (req, res) -> {
            Thread.sleep(5000);
            return res.json("5 segundos").status(200);
        });

        get("/users3", (req, res) -> {
            Thread.sleep(6000);
            return res.json("6 segundos").status(200);
        });

        get("/users4", (req, res) -> {
            return res.json("na hora!").status(200);
        });


    }

}
