package com.epam.framework.core.utils;

import com.epam.framework.core.exceptions.CollabFrameworkRuntimeException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
    private String file;
    private Properties properties;

    public PropertyReader(String file) {
        this.file = file;
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (FileReader configReader = new FileReader(FilenameUtils.normalize(System.getProperty("user.dir")
                +System.getProperty("file.separator")
                +file))) {


            properties.load(configReader);
        } catch (IOException e) {
            logger.info("Getting exception while reading the property File ({})", this.file);
            throw new CollabFrameworkRuntimeException("Getting exception while reading the property File ({})");
        }
    }

    /**
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns the
     * default value argument if the property is not found.
     *
     * @param key          the hashtable key.
     * @param defaultValue a default value.
     * @return the value in this property list with the specified key value.
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
