package com.rohila.api.service;

import com.rohila.api.dto.Trade;
import com.rohila.api.response.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Interface which is used to provide handling for post related endpoints
 *
 * @author Tarun Rohila
 */
@Validated
public interface TradeService {

    /**
     * Method to create new Trade
     *
     * @param trade - trade
     * @return added/updated trade
     */
    Response createOrUpdateTrade(Trade trade);

    /**
     * Method to update expiry flags of trades
     *
     */
    void updateExpiryFlags();
}
