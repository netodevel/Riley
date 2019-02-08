package br.com.riley_core.sample;

import br.com.riley_core.Riley;

import static java.util.Arrays.asList;

public class RileyApplication {

    public static void main(String[] args) throws Exception {
        new Riley()
                .start()
                .registerControllers(asList(new HelloWorldController()));
    }

}
