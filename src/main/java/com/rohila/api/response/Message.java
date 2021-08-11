package com.rohila.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rohila.api.util.SpelExpUtils;

import java.util.Objects;
import java.util.UUID;

/**
 * Class file to create messages
 *
 * @author Tarun Rohila
 */
@JsonInclude(Include.NON_EMPTY)
public class Message {

    /**
     * Variable declaration for id
     */
    private String id;

    /**
     * Variable declaration for code
     */
    private String code;

    /**
     * Variable declaration for title
     */
    private String title;

    /**
     * Variable declaration for detail
     */
    private String detail;

    /**
     * Constructor declaration
     */
    public Message() {
    }

    /**
     * Constructor declaration
     *
     * @param code   - code
     * @param title  - title
     * @param detail - detail
     */
    Message(String code, String title, String detail) {
        Objects.requireNonNull(code, "message code cant be null");
        Objects.requireNonNull(title, "message title can not be null");
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    /**
     * Constructor declaration
     *
     * @param id     - id
     * @param code   - code
     * @param title  - title
     * @param detail - detail
     */
    Message(String id, String code, String title, String detail) {
        Objects.requireNonNull(code, "message code cant be null");
        Objects.requireNonNull(title, "message title can not be null");
        this.id = id;
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    /**
     * Method to create message
     *
     * @param code  - code
     * @param title - title
     * @return message
     */
    public static Message create(String code, String title) {
        return new Message(code, title, null);
    }

    /**
     * Method to create message
     *
     * @param code   - code
     * @param title  - title
     * @param detail - detail
     * @return message
     */
    public static Message create(String code, String title, String detail) {
        return new Message(code, title, detail);
    }

    /**
     * Method to create message
     *
     * @param id     - id
     * @param code   - code
     * @param title  - title
     * @param detail - detail
     * @return message
     */
    public static Message create(String id, String code, String title, String detail) {
        return new Message(id, code, title, detail);
    }

    /**
     * Method to format title
     *
     * @param argument - argument
     * @return message
     */
    public Message formatTitle(String argument) {
        return new Message(code, SpelExpUtils.evalExp(title, argument), detail);
    }

    /**
     * Method to format title
     *
     * @param arguments - arguments
     * @return message
     */
    public Message formatTitle(Object... arguments) {
        return new Message(code, SpelExpUtils.evalExp(title, arguments), detail);
    }

    /**
     * Method to format detail
     *
     * @param arguments - arguments
     * @return message
     */
    public Message formatDetail(Object... arguments) {
        return new Message(code, title, SpelExpUtils.evalExp(detail, arguments));
    }

    /**
     * Method to format with detail
     *
     * @param detail - detail
     * @return message
     */
    public Message withDetail(String detail) {
        return new Message(code, title, detail);
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

    /**
     * Method to get the value of detail
     *
     * @return titdetaille - detail
     */
    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "Message{"
                + "id='"
                + id
                + '\''
                + ", code='"
                + code
                + '\''
                + ", title='"
                + title
                + '\''
                + ", detail='"
                + detail
                + '\''
                + '}';
    }
}
