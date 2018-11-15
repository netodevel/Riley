package br.com.rileyframework;


import lombok.Data;

import javax.servlet.Servlet;

@Data
public class Server {

    private Integer port;
    private Boolean isStaterd;
    private Servlet servlet;
}
