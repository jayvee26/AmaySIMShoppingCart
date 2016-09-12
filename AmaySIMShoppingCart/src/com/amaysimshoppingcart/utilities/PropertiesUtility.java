/*
 * This code is a property of AmaySIM.
 * Any change to this code must have permission of the company.
 */
package com.amaysimshoppingcart.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Jayvee
 * @since September 11, 2016
 * @version 1.0
 */
public class PropertiesUtility {
    
    /**
     * @param propertyFileName the filename of the property file
     * @return Properties the properties loaded from the properties file
     * @throws IOException 
     */
    public static Properties loadProperties(String propertyFileName) throws IOException {
        File file = new File(propertyFileName);
        String absolutePath = file.getAbsolutePath();
        Path configLocation = Paths.get(absolutePath);
        InputStream stream = Files.newInputStream(configLocation);
        Properties properties = new Properties();

        properties.load(stream);

        return properties;
    }
}
