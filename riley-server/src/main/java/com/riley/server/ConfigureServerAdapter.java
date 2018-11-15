package com.riley.server;

import javax.servlet.Servlet;

public interface ConfigureServerAdapter {

    void start() throws Exception;
    void shutDown() throws Exception;
    Boolean isStarted();
    Integer port();
    Servlet servlet();
}
