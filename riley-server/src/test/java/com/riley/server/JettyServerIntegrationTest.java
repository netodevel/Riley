package com.riley.server;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JettyServerIntegrationTest {

    private MockServlet mockServlet = new MockServlet();

    @Test
    public void dado_a_porta_3000_deve_startar_o_jetty_na_mesma() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(mockServlet)
                .jettyPort(3000)
                .build();

        jettyServer.start();
        assertEquals(3000, jettyServer.getServer().getURI().getPort());
        jettyServer.shutDown();
    }

    @Test
    public void dado_uma_porta_vazia_deve_startar_o_jetty_na_porta_3000() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(mockServlet)
                .build();

        jettyServer.start();
        assertEquals(3000, jettyServer.getServer().getURI().getPort());
        jettyServer.shutDown();
    }

}
