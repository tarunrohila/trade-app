package com.rohila.api.request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class file to create {@link RequestContext}
 *
 * @author Tarun Rohila
 */
public class RequestContext {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(RequestContext.class);

    /**
     * Variable declaration for contextValues
     */
    private Map<String, String> contextValues = new HashMap<>();

    /**
     * Constructor declaration
     *
     * @param request - request
     */
    public RequestContext(WebRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("null web request");
        }
        for (Iterator<String> names = request.getHeaderNames(); names.hasNext(); ) {
            String headerName = names.next();
            LOGGER.debug("Adding header in context value map header name = [{}]", headerName);
            contextValues.put(headerName, request.getHeader(headerName));
        }
    }

    /**
     * Method to get header from context values map
     *
     * @param key - key
     * @return header
     */
    protected String get(String key) {
        LOGGER.debug("Fetching header from context values map. header name = [{}]", key);
        return contextValues.get(key);
    }
}
