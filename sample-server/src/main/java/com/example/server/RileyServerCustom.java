package com.example.server;


import br.com.riley_core.Riley;

public class RileyServerCustom {

    public static void main(String[] args) throws Exception {
        Riley riley = Riley.getInstance();
        riley.configureServer(TomcatCustom.builder().build());
        riley.start();
    }

}
