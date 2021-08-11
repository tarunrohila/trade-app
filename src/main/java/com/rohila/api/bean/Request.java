package com.rohila.api.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohila.api.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * Class file to create object of Request
 *
 * @author Tarun Rohila
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Request implements Serializable {

    /**
     * Serial verion UID declaration
     */
    private static final long serialVersionUID = 1L;

    //** Logger declaration. */
    private static final Logger LOGGER = LogManager.getLogger(Request.class);

    /**
     * Variable declaration for data
     */
    Object data;

    /**
     * Method to get the value of data
     *
     * @return data - data
     */
    public <T> T getData() throws ClassCastException {
        return (T) this.data;
    }

    /**
     * Method to set the value of data
     *
     * @param data - data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Method to get data
     *
     * @param type - type
     * @param <T>  - type
     * @return data
     */
    public <T> T getData(Class<T> type) throws IllegalArgumentException {
        if (this.data == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value;
        try {
            value = mapper.convertValue(this.data, type);
        } catch (Exception e) {
            LOGGER.error("Could not convert request data to data type = [{}]", type.getName(), e);
            throw e;
        }
        return value;
    }

    /**
     * Method to get data usng typereference
     *
     * @param type - type
     * @param <T>  - type
     * @return data
     */
    public <T> T getData(TypeReference<T> type) throws IllegalArgumentException {
        if (this.data == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value;
        try {
            value = mapper.convertValue(this.data, type);
        } catch (Exception e) {
            LOGGER.error("Could not convert request data to data type = [{}]", type.getType(), e);
            throw e;
        }
        return value;
    }
}
