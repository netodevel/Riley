package br.com.rileyframework.sample;

import br.com.rileyframework.Riley;
import br.com.rileyframework.server.RileyServer;

public class RileyApplication {

    public static void main(String[] args) throws Exception {
        new Riley().init(new RileyServer());
    }

}
