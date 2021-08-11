package com.rohila.api.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohila.api.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * Class file to create object of {@link Resource}
 *
 * @author Tarun Rohila
 */
public class Resource<T> implements Serializable {

    /**
     * Serial verion UID declaration
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(Resource.class);

    /**
     * Variable declaration for id
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String id;

    /**
     * Variable declaration for type
     */
    private String type;

    /**
     * Variable declaration for attributes
     */
    private T attributes;

    /**
     * private constructor declaration
     */
    private Resource() {
    }

    /**
     * Constructor declaration
     *
     * @param type - type
     */
    public Resource(String type) {
        this.type = type;
    }

    /**
     * Constructor declaration
     *
     * @param id   - id
     * @param type - type
     */
    public Resource(String id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Constructor declaration
     *
     * @param type       - type
     * @param attributes - attributes object
     */
    public Resource(String type, T attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    /**
     * Constructor declaration
     *
     * @param id         - id
     * @param attributes - attributes object
     * @param type       - type
     */
    public Resource(String id, String type, T attributes) {
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    /**
     * Method to get the value of id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * Method to set the value of id
     *
     * @param id - id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to get the value of type
     *
     * @return type - type
     */
    public String getType() {
        return type;
    }

    /**
     * Method to set the value of type
     *
     * @param type - type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to get the value of attributes
     *
     * @return attributes - attributes
     */
    public T getAttributes() {
        return attributes;
    }

    /**
     * Method to set the value of attributes
     *
     * @param attributes - attributes
     */
    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }

    /**
     * Method to get attributes
     *
     * @param type - type
     * @param <T>  - type
     * @return attributes
     * @throws IllegalArgumentException
     */
    public <T> T getAttributes(Class<T> type) throws IllegalArgumentException {
        if (this.attributes == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value;
        try {
            value = mapper.convertValue(this.attributes, type);
        } catch (Exception e) {
            LOGGER.error("Could not convert resource data to data type = [{}]", type.getName(), e);
            throw e;
        }
        return value;
    }

    /**
     * Method to get attributes using type reference
     *
     * @param type - type
     * @param <T>  - type
     * @return attributes
     * @throws IllegalArgumentException
     */
    public <T> T getAttributes(TypeReference<T> type) throws IllegalArgumentException {
        if (this.attributes == null) {
            return null;
        }
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        T value;
        try {
            value = mapper.convertValue(this.attributes, type);
        } catch (Exception e) {
            LOGGER.error("Could not convert resource data to data type = [{}]", type.getType(), e);
            throw e;
        }
        return value;
    }

    @Override
    public String toString() {
        return "Resource{"
                + "id='"
                + id
                + '\''
                + ", type='"
                + type
                + '\''
                + ", attributes="
                + attributes
                + '}';
    }
}
