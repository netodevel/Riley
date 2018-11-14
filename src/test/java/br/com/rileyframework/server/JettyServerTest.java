package br.com.rileyframework.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JettyServerTest {

    @Test
    public void dado_um_valor_nulo_deve_retonar_porta_3000() {
        JettyServer jettyServer = JettyServer.builder()
                .build();
        Integer port = jettyServer.getServerPort();
        assertEquals((Integer)3000, port);
    }

    @Test
    public void dado_uma_porta_com_valor_diferente_deve_retonar_a_mesma() {
        JettyServer jettyServer = JettyServer.builder()
                .jettyPort(8080)
                .build();

        Integer port = jettyServer.getServerPort();
        assertEquals((Integer)8080, port);
    }

    @Test(expected = RileyServerException.class)
    public void dado_um_shutdown_sem_antes_startar_o_server_deve_lancar_uma_excecao() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .jettyPort(8080)
                .build();

        jettyServer.shutDown();
    }

}
