package com.rohila.api.controller;

import com.rohila.api.bean.Request;
import com.rohila.api.dto.Trade;
import com.rohila.api.response.Response;
import com.rohila.api.service.TradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.rohila.api.constant.RestRequestConstants.*;

/**
 * Class which is used to handle requests for Trades
 *
 * @author Tarun Rohila
 */
@RestController
public class TradeController {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(TradeController.class);

    /**
     * Autowired instance of tradeService
     */
    @Autowired
    @Qualifier("tradeService")
    private TradeService tradeService;

    /**
     * Method to create or update trade
     *
     * @param request - request
     * @return created post
     */
    @PostMapping(TRADES_ENDPOINT)
    public Response createOrUpdateTrade(@RequestBody Request request) {
        Trade trade = request.getData(Trade.class);
        LOGGER.debug("Adding or updating a new trade for tradeId = [{}]", trade.getTradeId());
        return tradeService.createOrUpdateTrade(trade);
    }
}
