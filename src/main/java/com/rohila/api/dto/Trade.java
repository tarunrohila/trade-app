package com.rohila.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

/**
 * Class which is used to create object of Trade
 *
 * @author Tarun Rohila
 */
public class Trade {

    /**
     * Variable declaration for id
     */
    private Long tradeId;

    /**
     * Variable declaration for version
     */
    private int version;

    /**
     * Variable declaration for counterPartyId
     */
    private String counterPartyId;

    /**
     * Variable declaration for bookId
     */
    private String bookId;

    /**
     * Variable declaration for maturityDate
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate maturityDate;

    /**
     * Variable declaration for createDate
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;

    /**
     * Variable declaration for expired
     */
    private String expired;

    /**
     * Method to get the value of tradeId
     *
     * @return tradeId - tradeId
     */
    public Long getTradeId() {
        return tradeId;
    }

    /**
     * Method to set the value of tradeId
     *
     * @param tradeId - tradeId
     */
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * Method to get the value of version
     *
     * @return version - version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Method to set the value of version
     *
     * @param version - version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Method to get the value of counterPartyId
     *
     * @return counterPartyId - counterPartyId
     */
    public String getCounterPartyId() {
        return counterPartyId;
    }

    /**
     * Method to set the value of counterPartyId
     *
     * @param counterPartyId - counterPartyId
     */
    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    /**
     * Method to get the value of bookId
     *
     * @return bookId - bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Method to set the value of bookId
     *
     * @param bookId - bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Method to get the value of maturityDate
     *
     * @return maturityDate - maturityDate
     */
    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    /**
     * Method to set the value of maturityDate
     *
     * @param maturityDate - maturityDate
     */
    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    /**
     * Method to get the value of createDate
     *
     * @return createDate - createDate
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Method to set the value of createDate
     *
     * @param createDate - createDate
     */
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    /**
     * Method to get the value of expired
     *
     * @return expired - expired
     */
    public String isExpired() {
        return expired;
    }

    /**
     * Method to set the value of expired
     *
     * @param expired - expired
     */
    public void setExpired(String expired) {
        this.expired = expired;
    }
}
