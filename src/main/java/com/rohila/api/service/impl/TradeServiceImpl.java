package com.rohila.api.service.impl;

import com.rohila.api.dto.Trade;
import com.rohila.api.gateway.TradeGateway;
import com.rohila.api.repository.domain.TradeDetails;
import com.rohila.api.response.Response;
import com.rohila.api.service.TradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.rohila.api.constant.AppConstants.ID;
import static com.rohila.api.constant.AppConstants.TRADES;
import static com.rohila.api.helper.CommonHelper.mapToDomain;
import static com.rohila.api.helper.CommonHelper.mapToResource;

/**
 * Class which is used to provide implementation of {@link TradeService}
 *
 * @author Tarun Rohila
 */
@Service("tradeService")
public class TradeServiceImpl implements TradeService {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(TradeServiceImpl.class);

    /**
     * Autowired instance of tradeGateway
     */
    @Autowired
    @Qualifier("tradeGateway")
    private TradeGateway tradeGateway;

    /**
     * Method to create new Trade
     *
     * @param trade - trade
     * @return added/updated trade
     */
    @Override
    public Response createOrUpdateTrade(Trade trade) {
        LOGGER.debug("Adding or updating a new trade for tradeId = [{}] in service layer", trade.getTradeId());
        return Response.assemble()
                .build(
                        mapToResource(
                                ID,
                                TRADES,
                                tradeGateway
                                        .createOrUpdateTrade(mapToDomain(trade, TradeDetails.class)),
                                Trade.class));
    }

    /**
     * Method to update expiry flags of trades
     */
    @Override
    public void updateExpiryFlags() {
        tradeGateway.updateExpiryFlags();
    }
}
