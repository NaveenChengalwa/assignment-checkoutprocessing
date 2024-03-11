package com.naveennaveen.shopnowautomation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for handling properties file.
 * Provides methods to load properties from a file and retrieve property values.
 */
public class UtilityClass {

    // Properties object to store key-value pairs from the properties file
    private static final Properties properties;

    // Static block to initialize the properties object with data from the properties file
    static {
        properties = new Properties();
        try {
            // Load properties from the TestData.properties file
            InputStream input = new FileInputStream("src/test/resources/TestData.properties");
            properties.load(input);
        } catch (IOException e) {
            // Log the error message if an error occurs while loading properties
            Logger.getLogger(UtilityClass.class.getName()).log(Level.SEVERE, "Error loading properties file", e);
        }
    }

    /**
     * Retrieves the value associated with the given key from the properties file.
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the given key.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
