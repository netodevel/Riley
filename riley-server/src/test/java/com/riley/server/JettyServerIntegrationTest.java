package com.riley.server;

import org.junit.Test;

import javax.servlet.*;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class JettyServerIntegrationTest {

    @Test
    public void dado_a_porta_3000_deve_startar_o_jetty_na_mesma() throws Exception {
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
                .jettyPort(3000)
                .build();

        jettyServer.start();
        assertEquals(3000, jettyServer.getServer().getURI().getPort());

        jettyServer.shutDown();
    }

    @Test
    public void dado_uma_porta_vazia_deve_startar_o_jetty_na_porta_3000() throws Exception {
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

        jettyServer.start();
        assertEquals(3000, jettyServer.getServer().getURI().getPort());

        jettyServer.shutDown();
    }

}
