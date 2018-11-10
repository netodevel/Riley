package br.com.rileyframework.server;

import br.com.rileyframework.Riley;
import org.junit.After;
import org.junit.Test;

public class RileyServerTest {

    private static final Riley riley = Riley.getInstance();
    private RileyServer rileyServer;

    @Test
    public void dado_um_server_default_deve_startar_o_jetty() throws InterruptedException {
        rileyServer = new RileyServer();
        riley.init(rileyServer);
    }

    @Test(expected = RileyServerException.class)
    public void dado_um_server_nao_implementado_deve_lancar_excecao() {
        rileyServer = new RileyServer("Netty");
        riley.init(rileyServer);
    }

    @After
    public void tearDown() throws Exception {
        rileyServer.shutDown();
    }

}
