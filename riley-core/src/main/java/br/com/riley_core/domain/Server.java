package br.com.riley_core.domain;

import lombok.Builder;
import lombok.Data;

import javax.servlet.Servlet;

@Data
@Builder
public class Server {

    private Integer port;
    private Boolean isStaterd;
    private Servlet servlet;
}
