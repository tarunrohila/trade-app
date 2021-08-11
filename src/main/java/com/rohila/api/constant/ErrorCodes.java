package com.rohila.api.constant;

/**
 * Enum which is used to create constants
 *
 * @author Tarun Rohila
 */
public enum ErrorCodes {

    /**
     * All error codes
     */
    LOWER_VERSION("API_INT_001", "Lower version of trade"),
    INVALID_MATURITY_DATE("API_INT_002", "Invalid Maturity Date"),
    INTERNAL_SERVER("API_INT_003", "Internal Server Error"),
    MISSING_MANDATORY_HEADERS("API_INT_004", "Mandatory Headers Error");

    /**
     * Variable declaration for code
     */
    public String code;

    /**
     * Variable declaration for detail
     */
    public String title;

    /**
     * Method to set error code
     *
     * @param code - code
     */
    private ErrorCodes(String code) {
        this.code = code;
    }

    /**
     * Method to set error code and details
     *
     * @param code - code
     */
    private ErrorCodes(String code, String title) {
        this.code = code;
        this.title = title;
    }

    /**
     * Method to get the value of code
     *
     * @return code - code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to get the value of title
     *
     * @return title - title
     */
    public String getTitle() {
        return title;
    }
}
