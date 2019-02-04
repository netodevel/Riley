package br.com.riley_core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RileyOutput {

    public static final String DEFAULT_BANNER = "\n" +
            "____       ____  _ __              \n" +
            "\\ \\ \\     / __ \\(_) /__  __  __    \n" +
            " \\ \\ \\   / /_/ / / / _ \\/ / / /    \n" +
            " / / /  / _, _/ / /  __/ /_/ /     \n" +
            "/_/_/  /_/ |_/_/_/\\___/\\__, /      \n" +
            "                      /____/       \n";

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    public void captureOutput() {
        System.setErr(new PrintStream(errContent));
        System.out.println("Starting server...");
    }

}
