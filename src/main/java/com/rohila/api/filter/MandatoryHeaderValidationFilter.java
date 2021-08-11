package com.rohila.api.filter;

import com.rohila.api.constant.ErrorMessageConstants;
import com.rohila.api.exception.ApiRequestException;
import com.rohila.api.exception.CoreExceptionHandler;
import com.rohila.api.request.InternalRequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class file to create filter to validate mandatory headaers
 *
 * @author Tarun Rohila
 */
@Component
@Order(20)
public class MandatoryHeaderValidationFilter extends OncePerRequestFilter {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(MandatoryHeaderValidationFilter.class);

    /**
     * Constant declaration for healthcheck actuator uri
     */
    private static final String HEALTHCHECK_ACTUATOR_URI = "/actuator";

    /**
     * Constant declaration for H2_CONSOLE_URI uri
     */
    private static final String H2_CONSOLE_URI = "/h2-console";

    /**
     * Constant declaration for SWAGGER_DOC uri
     */
    private static final String SWAGGER_DOC = "/api-docs";

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
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        LOGGER.debug("Checking mandatory header filter");
        String path =
                request
                        .getRequestURI()
                        .substring(request.getContextPath().length())
                        .replaceAll("[/]+$", "");
        boolean allowedPath = path.startsWith(HEALTHCHECK_ACTUATOR_URI) || path.startsWith(H2_CONSOLE_URI) || path.contains(SWAGGER_DOC);
        LOGGER.debug("Request Path = [{}]", path);
        boolean isCorrelationIdPresent = checkCorrelationHeaderPresent(request, response);
        if (!isCorrelationIdPresent && !allowedPath) {
            LOGGER.error("Mandatory header is not present");
            throwMissingHeaderException(InternalRequestContext.CORRELATION_ID, request, response);
        } else {
            LOGGER.debug("All mandatory headers are present so continuing the request");
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Overridden method to set not to filter when Async dispatch
     *
     * @return shouldNotFilterAsyncDispatch
     */
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    /**
     * Method to throw missing header exception
     *
     * @param correlationId - correlation id
     * @param request       - request
     * @param response      - response
     */
    private void throwMissingHeaderException(
            String correlationId,
            HttpServletRequest request,
            HttpServletResponse response) {
        LOGGER.debug("Throwing missing header exception");
        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        CoreExceptionHandler handler = ctx.getBean(CoreExceptionHandler.class);
        ModelAndView result =
                handler.resolveException(
                        request,
                        response,
                        null,
                        new ApiRequestException(
                                ErrorMessageConstants.MISSING_MANDATORY_HEADERS_ERROR.formatTitle(
                                        InternalRequestContext.CORRELATION_ID)));
        try {
            if (result != null) {
                result.getView().render(result.getModel(), request, response);
                LOGGER.debug("View is created for missing header");
            }
        } catch (Exception e) {
            LOGGER.error("Unexpceted exception occured while rendering view ", e);
            throw new RuntimeException("Unexpected exception occured");
        }
    }

    /**
     * Method to check correlation id is present in header
     *
     * @param request  - request
     * @param response - response
     * @return checkCorrelationHeaderPresent
     */
    private boolean checkCorrelationHeaderPresent(
            HttpServletRequest request, HttpServletResponse response) {
        boolean isPresent = false;
        if (StringUtils.hasText(request.getHeader(InternalRequestContext.CORRELATION_ID))) {
            response.setHeader(InternalRequestContext.CORRELATION_ID, request.getHeader(
                    InternalRequestContext.CORRELATION_ID));
            isPresent = true;
        }
        return isPresent;
    }
}
