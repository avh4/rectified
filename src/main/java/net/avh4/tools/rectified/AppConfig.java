package net.avh4.tools.rectified;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class AppConfig {
    public String appName() {
        return "Rectified α";
    }

    public String defaultDesign() {
        try {
            return IOUtils.toString(getClass().getResourceAsStream("example.lisp"));
        } catch (IOException e) {
            System.out.println("Failed to load example");
            e.printStackTrace();
            return "(design ((color (rgb \"#eeeeee\"))))";
        }
    }
}
