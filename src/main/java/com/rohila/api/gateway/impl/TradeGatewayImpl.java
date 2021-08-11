package com.rohila.api.gateway.impl;

import com.rohila.api.exception.ApiRequestException;
import com.rohila.api.gateway.TradeGateway;
import com.rohila.api.repository.TradeRepository;
import com.rohila.api.repository.domain.TradeDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.rohila.api.constant.ErrorMessageConstants.INVALID_MATURITY_DATE_ERROR;
import static com.rohila.api.constant.ErrorMessageConstants.LOWER_VERSION_ERROR;

/**
 * Class which is used to provide implementation for gateway requests for Trade  Operations
 *
 * @author Tarun Rohila
 */
@Repository("tradeGateway")
public class TradeGatewayImpl implements TradeGateway {

    /**
     * Logger declaration.
     */
    private static final Logger LOGGER = LogManager.getLogger(TradeGatewayImpl.class);

    /**
     * Autowired instance of tradeRepository
     */
    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Method to create new Trade
     *
     * @param trade - trade
     * @return
     */
    @Override
    public TradeDetails createOrUpdateTrade(TradeDetails trade) {
        Optional<TradeDetails> tradeDetails = tradeRepository.findById(trade.getTradeId());
        if(tradeDetails.isPresent() && tradeDetails.get().getVersion() > trade.getVersion()) {
            LOGGER.error("Version is lower than the current one");
            throw new ApiRequestException(LOWER_VERSION_ERROR.formatDetail(trade.getVersion(), tradeDetails.get().getVersion()));
        } else if(tradeDetails.isPresent() && trade.getMaturityDate().isBefore(LocalDate.now())) {
            LOGGER.error("Maturity date is less than today");
            throw new ApiRequestException(INVALID_MATURITY_DATE_ERROR.formatDetail(trade.getMaturityDate().toString()));
        } else if(tradeDetails.isPresent() && tradeDetails.get().getVersion() == trade.getVersion()){
            return tradeRepository.save(tradeDetails.get());
        } else {
            return tradeRepository.save(trade);
        }
    }

    /**
     * Method to update expiry flags of trades
     */
    @Override
    public void updateExpiryFlags() {
        tradeRepository.findAll().stream().filter(tradeDetails -> tradeDetails.getMaturityDate().isBefore(LocalDate.now())).forEach(tradeDetails -> {
            tradeDetails.setExpired("Y");
            LOGGER.debug("Updating Trade to set it expired for tradeId = [{}}" , tradeDetails.getTradeId());
            tradeRepository.save(tradeDetails);
        });
    }
}
