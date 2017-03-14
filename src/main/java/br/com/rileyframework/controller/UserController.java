package br.com.rileyframework.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.model.User;
import br.com.rileyframework.serialization.Render;

@Rest
public class UserController {

    @Get("/users")
    public String index() {
        List<User> users = new ArrayList<User>();
        users.add(new User("1", "josevieira.dev@gmail.com", "password"));
        users.add(new User("2", "netodevel@gmail.com", "password"));
        return Render.toJson(users);
    }

}