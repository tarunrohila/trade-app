package com.rohila.api.constant;

import com.rohila.api.response.Message;

import static com.rohila.api.constant.ErrorCodes.*;
import static com.rohila.api.constant.ErrorCodes.INTERNAL_SERVER;

/**
 * Class which is used to keep constants for error messages
 *
 * @author Tarun Rohila
 */
public class ErrorMessageConstants {

    /**
     * Private constructor
     */
    private ErrorMessageConstants() {
    }

    /**
     * Constant declaration for ARG_ZERO
     */
    public static final String ARG_ZERO = "${arg[0]}";

    /**
     * Constant declaration for ARG_ONE
     */
    public static final String ARG_ONE = "${arg[1]}";

    /**
     * Constant declaration for MISSING_MANDATORY_HEADERS_ERROR
     */
    public static final Message MISSING_MANDATORY_HEADERS_ERROR =
            Message.create(MISSING_MANDATORY_HEADERS.getCode(), MISSING_MANDATORY_HEADERS.getTitle(), "Mandatory header Correlation-ID is missing in the request.");

    /**
     * Constant declaration for UNAUTHORIZED_ACCESS_ERROR
     */
    public static final Message LOWER_VERSION_ERROR =
            Message.create(LOWER_VERSION.getCode(), LOWER_VERSION.getTitle(), "Trade version = ${arg[0]} is lower than the current version = ${arg[1]}");

    /**
     * Constant declaration for INVALID_MATURITY_DATE_ERROR
     */
    public static final Message INVALID_MATURITY_DATE_ERROR =
            Message.create(INVALID_MATURITY_DATE.getCode(), INVALID_MATURITY_DATE.getTitle(), "Invalid maturity date = ${arg[0]} which is less than today");

    /**
     * Constant declaration for INTERNAL_SERVER_ERROR
     */
    public static final Message INTERNAL_SERVER_ERROR =
            Message.create(INTERNAL_SERVER.getCode(), INTERNAL_SERVER.getTitle());
}
