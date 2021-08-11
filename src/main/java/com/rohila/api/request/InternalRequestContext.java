package com.rohila.api.request;

import org.springframework.web.context.request.WebRequest;

/**
 * Class file to create {@link InternalRequestContext}
 *
 * @author Tarun Rohila
 */
public class InternalRequestContext extends RequestContext {

    /**
     * Constant declaration for CORRELATION_ID
     */
    public static final String CORRELATION_ID = "Correlation-Id";

    /**
     * Constructor declaration
     *
     * @param request - request
     */
    public InternalRequestContext(WebRequest request) {
        super(request);
    }

    /**
     * Method to get Correlation-Id
     *
     * @return Correlation-Id
     */
    public String getCorrelationId() {
        return get(CORRELATION_ID);
    }
}
