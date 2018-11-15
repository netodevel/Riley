package br.com.rileyframework;

import com.riley.server.ConfigureServerAdapter;
import com.riley.server.RileyServerException;
import org.junit.Test;

import javax.servlet.Servlet;

public class RileyServerUnitTest {

    private static final Riley riley = Riley.getInstance();

    @Test(expected = RileyServerException.class)
    public void dado_um_shutdown_sem_startar_o_server_deve_lancar_um_excecao() throws Exception {
        riley.shutDown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void dado_um_servidor_customizado_com_porta_nula_deve_lancar_excecao() throws Exception {
        ConfigureServerAdapter configureServerAdapter = new ConfigureServerAdapter() {
            @Override
            public void start() throws Exception {
            }

            @Override
            public void shutDown() throws Exception {
            }

            @Override
            public Boolean isStarted() {
                return false;
            }

            @Override
            public Integer port() {
                return null;
            }

            @Override
            public Servlet servlet() {
                return null;
            }
        };

        riley.configureServer(configureServerAdapter);
        riley.start();
    }

    @Test(expected = IllegalArgumentException.class)
    public void dado_um_servidor_customizado_com_servlet_nulo_deve_lancar_excecao() throws Exception {
        ConfigureServerAdapter configureServerAdapter = new ConfigureServerAdapter() {
            @Override
            public void start() throws Exception {
            }

            @Override
            public void shutDown() throws Exception {
            }

            @Override
            public Boolean isStarted() {
                return false;
            }

            @Override
            public Integer port() {
                return 3000;
            }

            @Override
            public Servlet servlet() {
                return null;
            }
        };

        riley.configureServer(configureServerAdapter);
        riley.start();
    }

}
