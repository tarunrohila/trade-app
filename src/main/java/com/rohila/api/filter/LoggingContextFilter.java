package com.rohila.api.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Abstract logging context filter class
 *
 * @author Tarun Rohila
 */
public abstract class LoggingContextFilter extends OncePerRequestFilter {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(LoggingContextFilter.class);

    /**
     * Overridden method to filter
     *
     * @param request     - request
     * @param response    - response
     * @param filterChain - filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LOGGER.debug("Handling Logging in LoggingContextFilter");
        Map<String, String> context = getLoggingContextProperties(request);
        for (Map.Entry<String, String> map : context.entrySet()) {
            LOGGER.debug(
                    "Adding Logging properties key [{}] value [{}] = " + map.getKey(), map.getValue());
            MDC.put(map.getKey(), map.getValue());
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    /**
     * MEthod to get logging context property
     *
     * @param request - request
     * @return logging context properties
     */
    protected abstract Map<String, String> getLoggingContextProperties(HttpServletRequest request);

    /**
     * Overridden method to set not to filter when Async dispatch
     *
     * @return shouldNotFilterAsyncDispatch
     */
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }
}
