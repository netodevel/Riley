package br.com.rileyframework.exceptions;

import lombok.Data;

@Data
public class RileyException extends RuntimeException {

    private String reason;
    private String description;

    public RileyException(String reason, String description) {
        this.reason = reason;
        this.description = description;
    }

}
