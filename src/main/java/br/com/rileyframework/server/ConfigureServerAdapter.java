package br.com.rileyframework.server;

import br.com.rileyframework.Riley;
import br.com.rileyframework.RileyServlet;

import javax.servlet.Servlet;

public interface ConfigureServerAdapter {

    void start() throws Exception;
    void shutDown() throws Exception;

    default Servlet servlet() {
        return new RileyServlet(Riley.getInstance());
    }

    Integer port();
    Boolean isStarted();
}
