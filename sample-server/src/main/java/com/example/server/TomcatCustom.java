package com.example.server;

import br.com.riley_core.Riley;
import br.com.riley_core.servlet.RileyServlet;
import com.riley.server.ConfigureServerAdapter;
import lombok.Builder;
import lombok.Data;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.Servlet;
import java.io.File;

@Data
@Builder
public class TomcatCustom implements ConfigureServerAdapter {

    private static final int DEFAULT_TOMCAT_PORT = 8080;
    private Tomcat tomcat;
    private Integer port;

    @Override
    public void start() throws Exception {
        tomcat = new Tomcat();
        tomcat.setPort(getTomcatPort());
        String contextPath = "/";
        String docBase = new File(".").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, docBase);
        //tomcat.addServlet("/", "riley_servlet", servlet());
        context.addServletMappingDecoded("/", "riley_servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    private int getTomcatPort() {
        return port != null ? port : DEFAULT_TOMCAT_PORT;
    }

    @Override
    public void shutDown() throws Exception {
        tomcat.stop();
        tomcat.destroy();
    }

    @Override
    public Boolean isStarted() {
        if (tomcat == null) return false;
        return tomcat.getServer().getState().isAvailable();
    }

    @Override
    public Integer port() {
        return getTomcatPort();
    }

    @Override
    public Servlet servlet() {
        return new RileyServlet(Riley.getInstance());
    }

}
