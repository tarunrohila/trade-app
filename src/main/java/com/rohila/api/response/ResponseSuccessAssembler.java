package com.rohila.api.response;

import org.springframework.http.HttpStatus;

/**
 * Interface to create {@link ResponseSuccessAssembler}
 *
 * @author Tarun Rohila
 */
public interface ResponseSuccessAssembler {

    /**
     * Method to build response
     *
     * @param payload - payload
     * @return response
     */
    Response build(Object payload);

    /**
     * Method to build response
     *
     * @param status  - status
     * @param payload - payload
     * @return response
     */
    Response build(HttpStatus status, Object payload);
}
