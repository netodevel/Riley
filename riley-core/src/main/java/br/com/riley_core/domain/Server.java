package br.com.riley_core.domain;


import lombok.Data;

import javax.servlet.Servlet;

@Data
public class Server {

    private Integer port;
    private Boolean isStaterd;
    private Servlet servlet;
}
