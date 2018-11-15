package br.com.rileyframework;

import com.riley.server.JettyServer;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RileyServerIntegrationTest {

    private static final Riley riley = Riley.getInstance();

    @Test
    public void dado_um_server_com_boundary_deve_startar_o_server() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(new RileyServlet(riley))
                .build();

        riley.configureServer(jettyServer);
        riley.start();

        assertTrue(riley.getServer().getIsStaterd());

        riley.shutDown();
    }

    @Test
    public void dado_um_server_sem_declarar_porta_com_boundary_deve_startar_na_porta_3000() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(new RileyServlet(riley))
                .build();

        riley.configureServer(jettyServer);
        riley.start();

        assertEquals((Integer) 3000, riley.getServer().getPort());

        riley.shutDown();
    }

    @Test
    public void dado_configure_server_nullo_deve_startar_jetty_como_default() throws Exception {
        riley.start();

        assertTrue(riley.getServer().getIsStaterd());

        riley.shutDown();
    }

    @Test
    public void dado_o_start_sem_boundary_de_server_deve_startar_o_jetty_com_riley_servlet() throws Exception {
        riley.start();

        assertNotNull(riley.getServer().getServlet());

        riley.shutDown();
    }

}
