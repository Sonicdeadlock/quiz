package io.sonicdeadlock.quiz.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alex on 9/25/2016.
 */
public class PropertiesLoader {
    private static final Properties PROPERTIES;
    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(PropertiesLoader.class.getClassLoader().getResourceAsStream("quiz.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName){
        return  PROPERTIES.getProperty(propertyName);
    }
}
