package br.com.rileyframework.utils;

import br.com.rileyframework.exceptions.RileyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SetupLoader {

    public String basePackage() throws IOException {
        BufferedReader readArq = new BufferedReader(new FileReader(getUserDir() + "/src/main/resources/setup.conf"));
        String row = readArq.readLine();
        while (row != null) {
            String [] values = row.split(":");
            if (values[0].equals("base-package")) {
                return values[1];
            }
        }
        System.out.println("[ERROR] ==> error: could not find base-package in setup.conf");
        throw new RileyException("Configuration", "could not find base-package in setup.conf");
    }

    public String getUserDir() {
        return System.getProperty("user.dir");
    }

}
