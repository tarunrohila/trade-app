package com.rohila.api.gateway;

import com.rohila.api.repository.domain.TradeDetails;
import org.springframework.stereotype.Repository;

/**
 * Interface which is used to handle gateway requests for Trade Operations
 *
 * @author Tarun Rohila
 */
public interface TradeGateway {

    /**
     * Method to create new Trade
     *
     * @param trade - trade
     * @return
     */
    TradeDetails createOrUpdateTrade(TradeDetails trade);

    /**
     * Method to update expiry flags of trades
     */
    void updateExpiryFlags();
}
