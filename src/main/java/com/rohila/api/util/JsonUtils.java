package com.rohila.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Class file to provide methods for json
 *
 * @author Tarun Rohila
 */
public class JsonUtils {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method to get instance of object mapper
     *
     * @return objectmapper
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
