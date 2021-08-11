package com.rohila.api.exception;

import com.rohila.api.constant.ErrorMessageConstants;
import com.rohila.api.response.Message;
import com.rohila.api.response.Response;
import com.rohila.api.response.ResponseAssembler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class file to handle core exception
 *
 * @author Tarun Rohila
 */
public class CoreExceptionHandler extends AbstractCoreExceptionHandler {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(CoreExceptionHandler.class);

    /**
     * Method to get exception order
     *
     * @return order
     */
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE - 10;
    }

    /**
     * Method to handle exceptions and resolve view
     *
     * @param request  - httpServletRequest
     * @param response - httpServletResponse
     * @param handler  - handler
     * @param e        - exception
     * @return object
     */
    @Override
    protected Object handleException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        if (e instanceof ApiException) {
            LOGGER.error("Custom API Exception", e);
            ApiException exception = (ApiException) e;
            response.setStatus(exception.getStatus().value());
            ResponseAssembler assembler = Response.assemble();
            for (Message errorMessage : exception.getMessages()) {
                assembler.addErrorMesage(errorMessage);
            }
            return assembler.build(exception.getStatus().value());

        } else {
            LOGGER.error("Unhandled Exception", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return Response.assemble()
                    .addErrorMesage(ErrorMessageConstants.INTERNAL_SERVER_ERROR)
                    .build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
