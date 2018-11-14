package br.com.rileyframework;

import br.com.rileyframework.server.ConfigureServerAdapter;
import br.com.rileyframework.server.RileyServerException;
import org.junit.Test;

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
            public Integer port() {
                return null;
            }
        };

        riley.configureServer(configureServerAdapter);
        riley.start();
    }
}
