package com.rohila.api.response;

import org.springframework.http.HttpStatus;

import java.util.Collection;

/**
 * Interface to create {@link ResponseErrorAssembler}
 *
 * @author Tarun Rohila
 */
public interface ResponseErrorAssembler {

    /**
     * Method to add error message
     *
     * @param errorMessage - errorMessage
     * @return response error assembler
     */
    ResponseErrorAssembler addErrorMesage(Message errorMessage);

    /**
     * Method to build response
     *
     * @param errorStatus - errorStatus
     * @return response
     */
    Response build(HttpStatus errorStatus);
}
