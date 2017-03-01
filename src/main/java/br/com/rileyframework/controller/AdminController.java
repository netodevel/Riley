package br.com.rileyframework.controller;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;

@Rest
public class AdminController {
	
	@Get("/index")
	public String index() {
		return "index";
	}

}
