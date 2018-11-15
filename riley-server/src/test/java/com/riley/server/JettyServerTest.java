package com.riley.server;

import org.junit.Test;

import javax.servlet.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JettyServerTest {

    @Test
    public void dado_um_valor_nulo_deve_retonar_porta_3000() {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(new Servlet() {
                    @Override
                    public void init(ServletConfig servletConfig) throws ServletException {
                    }

                    @Override
                    public ServletConfig getServletConfig() {
                        return null;
                    }

                    @Override
                    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
                    }

                    @Override
                    public String getServletInfo() {
                        return null;
                    }

                    @Override
                    public void destroy() {
                    }
                })
                .build();
        Integer port = jettyServer.getServerPort();
        assertEquals((Integer)3000, port);
    }

    @Test
    public void dado_uma_porta_com_valor_diferente_deve_retonar_a_mesma() {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(new Servlet() {
                    @Override
                    public void init(ServletConfig servletConfig) throws ServletException {
                    }

                    @Override
                    public ServletConfig getServletConfig() {
                        return null;
                    }

                    @Override
                    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
                    }

                    @Override
                    public String getServletInfo() {
                        return null;
                    }

                    @Override
                    public void destroy() {
                    }
                })
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

    @Test(expected = RileyServerException.class)
    public void dado_start_sem_servlet_configurado_deve_lancar_uma_excecao() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .jettyPort(8080)
                .build();

        jettyServer.start();
        jettyServer.shutDown();
    }

    @Test
    public void dado_start_com_servlet_configurado_deve_executar_o_servidor() throws Exception {
        JettyServer jettyServer = JettyServer.builder()
                .servlet(new Servlet() {
                    @Override
                    public void init(ServletConfig servletConfig) throws ServletException {
                    }

                    @Override
                    public ServletConfig getServletConfig() {
                        return null;
                    }

                    @Override
                    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
                    }

                    @Override
                    public String getServletInfo() {
                        return null;
                    }

                    @Override
                    public void destroy() {
                    }
                })
                .jettyPort(8080)
                .build();

        jettyServer.start();

        assertTrue(jettyServer.isStarted());

        jettyServer.shutDown();
    }

}
