package com.rohila.api.response;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohila.api.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class file to create Response object
 *
 * @author Tarun Rohila
 */
@JsonInclude(Include.NON_EMPTY)
@JsonFilter("DataFilter")
public class Response {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(Response.class);

    /**
     * variable declaration for headers
     */
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

    /**
     * Variable declaration for httpStatus
     */
    HttpStatus httpStatus;


    /**
     * Variable declaration for data
     */
    Object data = new DefaultData();

    /**
     * Variable declaration for error messages
     */
    List<Message> errors;

    /**
     * Private constructor declaration
     */
    private Response() {
    }

    /**
     * method to assemble resposne
     *
     * @return response
     */
    public static ResponseAssembler assemble() {
        LOGGER.debug("ResponseAssembler is created with empty response");
        return ResponseAssembler.assemble(new Response());
    }


    /**
     * Method to generate empty response
     *
     * @param status - status
     * @return response
     */
    public static Response empty(HttpStatus status) {
        LOGGER.debug("Creating response with response http status = [{}]", status);
        if (status == null) {
            throw new IllegalArgumentException("status cannot be empty");
        }
        Response response = new Response();
        response.httpStatus = status;
        return response;
    }


    /**
     * Method to get the value of data
     *
     * @return data - data
     */
    @SuppressWarnings("unchecked")
    @JsonInclude(Include.ALWAYS)
    public <T extends Object> T getData() throws ClassCastException {
        return (T) this.data;
    }

    /**
     * Method to set the value of data
     *
     * @param data - data
     */
    public void setData(Object data) {
        LOGGER.debug("Setting data in the response");
        this.data = data;
    }

    /**
     * Method to get the value of data
     *
     * @return type - type
     */
    public <T extends Object> T getData(Class<T> type) throws ClassCastException {
        if (this.data == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value = null;
        try {
            value = mapper.convertValue(this.data, type);
        } catch (Exception e) {
            LOGGER.debug("Could not convert response data to type = [{}]", type.getName(), e);
            throw e;
        }
        return value;
    }

    /**
     * Method to get the value of data
     *
     * @return data - data
     */
    public <T extends Object> T getData(TypeReference<T> type) throws ClassCastException {
        if (this.data == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value = null;
        try {
            value = mapper.convertValue(this.data, type);
        } catch (Exception e) {
            LOGGER.debug("Could not convert response data to type = [{}]", type.getType(), e);
            throw e;
        }
        return value;
    }

    /**
     * Method to get the value of errors
     *
     * @return errors - errors
     */
    public List<Message> getErrors() {
        LOGGER.debug("Retrieving errors from response");
        if (errors == null) {
            LOGGER.debug("Response error is null");
            return null;
        }
        LOGGER.debug("Number of errors are : [{}]", errors.size());
        return Collections.unmodifiableList(errors);
    }

    /**
     * Method to set the value of errors
     *
     * @param errors - errors
     */
    public void setErrors(List<Message> errors) {
        LOGGER.debug("Setting errors in the response");
        this.errors = errors;
    }

    /**
     * Method to get the value of headers
     *
     * @return headers - headers
     */
    @JsonIgnore
    public MultiValueMap<String, String> getMultiValuedHeaders() {
        if (headers == null) {
            return null;
        }
        return CollectionUtils.unmodifiableMultiValueMap(headers);
    }

    /**
     * Method to set the value of headers
     *
     * @param headers - headers
     */
    public void setHeaders(Map<String, String> headers) {
        if (this.headers == null) {
            this.headers = new LinkedMultiValueMap<>();
        }
        this.headers.clear();
        this.headers.setAll(headers);
    }

    /**
     * Method to get multi value headers
     *
     * @return headers
     */
    @JsonIgnore
    public MultiValueMap<String, String> getMultiValueHeaders() {
        LOGGER.debug("Retrieving headers from response");
        if (headers == null) {
            return null;
        }
        return CollectionUtils.unmodifiableMultiValueMap(headers);
    }

    /**
     * Method to set the value of headers
     *
     * @param headers - headers
     */
    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Method to get the value of httpStatus
     *
     * @return httpStatus - httpStatus
     */
    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Method to set the value of httpStatus
     *
     * @param httpStatus - httpStatus
     */
    @JsonIgnore
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "Response{"
                + "headers="
                + headers
                + ", httpStatus="
                + httpStatus
                + ", data="
                + data
                + ", errors="
                + errors
                + '}';
    }
}
