package com.utils;

import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class SchemaLoader {
    public static String loadSchema(String name){
        try {
            return IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(name), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

