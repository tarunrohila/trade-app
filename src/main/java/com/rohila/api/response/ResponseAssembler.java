package com.rohila.api.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class file to create object for {@link ResponseAssembler}
 *
 * @author Tarun Rohila
 */
public class ResponseAssembler implements ResponseSuccessAssembler, ResponseErrorAssembler {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(ResponseAssembler.class);

    /**
     * Variable declaration for response
     */
    private Response responseToAssemble;

    /**
     * Constructor declaration
     *
     * @param response - response
     */
    protected ResponseAssembler(Response response) {
        LOGGER.debug("Response Assembler is created with provided response");
        LOGGER.trace("Response is [{}]");
        this.responseToAssemble = response;
    }

    /**
     * MEthod to assemble response
     *
     * @param response response
     * @return response assembler
     */
    static ResponseAssembler assemble(Response response) {
        return new ResponseAssembler(response);
    }


    /**
     * Method to build response
     *
     * @param payload - payload
     * @return response
     */
    @Override
    public Response build(Object payload) {
        LOGGER.debug("Building response with payload and default response status : {}", HttpStatus.OK);
        responseToAssemble.data = payload;
        responseToAssemble.httpStatus = HttpStatus.OK;
        return build();
    }

    /**
     * Method to build response
     *
     * @param status  - status
     * @param payload - payload
     * @return response
     */
    @Override
    public Response build(HttpStatus status, Object payload) {
        LOGGER.debug("Building response with payload and response status : {}", status);
        responseToAssemble.data = payload;
        responseToAssemble.httpStatus = status;
        return build();
    }

    /**
     * Method to add error message
     *
     * @param errorMessage - errorMessage
     * @return response error assembler
     */
    @Override
    public ResponseErrorAssembler addErrorMesage(Message errorMessage) {
        LOGGER.debug("Adding erro message in the response status : {}", errorMessage);
        if (errorMessage == null) {
            return this;
        }
        if (responseToAssemble.errors == null) {
            responseToAssemble.errors = new ArrayList<Message>();
        }
        responseToAssemble.errors.add(errorMessage);
        return this;
    }

    /**
     * Method to build response
     *
     * @param errorStatus - errorStatus
     * @return response
     */
    @Override
    public Response build(HttpStatus errorStatus) {
        LOGGER.debug("Building response with respone status : {}", errorStatus);
        responseToAssemble.httpStatus = errorStatus;
        return build();
    }

    /**
     * Method to build final respone
     *
     * @return response
     */
    Response build() {
        LOGGER.debug("bulding final response");
        if (responseToAssemble.httpStatus == null) {
            throw new IllegalStateException("cannot build a response without http status");
        }
        return responseToAssemble;
    }
}
