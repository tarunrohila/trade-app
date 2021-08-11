package com.rohila.api.service;

import com.rohila.api.bean.Request;
import com.rohila.api.bean.Resource;
import com.rohila.api.controller.TradeController;
import com.rohila.api.dto.Trade;
import com.rohila.api.gateway.TradeGateway;
import com.rohila.api.repository.domain.TradeDetails;
import com.rohila.api.response.Response;
import com.rohila.api.service.impl.TradeServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;


@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

    @InjectMocks
    TradeServiceImpl tradeService;

    @Mock
    TradeGateway tradeGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateTrade() {
        Request request = new Request();
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTradeId(new Long(1));
        tradeDetails.setBookId("b2");
        tradeDetails.setCounterPartyId("CP1");
        tradeDetails.setMaturityDate(LocalDate.now());
        Trade trade = new Trade();
        trade.setTradeId(new Long(1));
        trade.setBookId("b2");
        trade.setCounterPartyId("CP1");
        trade.setMaturityDate(LocalDate.now());
        Mockito.when(tradeGateway.createOrUpdateTrade(Mockito.any(TradeDetails.class))).thenReturn(tradeDetails);
        Response response = tradeService.createOrUpdateTrade(trade);
        Assert.assertNotNull(response);
    }

    @Test
    void testCreateTradeWhenError() {
        Request request = new Request();
        TradeDetails tradeDetails = new TradeDetails();
        tradeDetails.setTradeId(new Long(1));
        tradeDetails.setBookId("b2");
        tradeDetails.setCounterPartyId("CP1");
        tradeDetails.setMaturityDate(LocalDate.now());
        Trade trade = new Trade();
        trade.setTradeId(new Long(1));
        trade.setBookId("b2");
        trade.setCounterPartyId("CP1");
        trade.setMaturityDate(LocalDate.now());
        Mockito.when(tradeGateway.createOrUpdateTrade(Mockito.any(TradeDetails.class))).thenThrow(new IllegalStateException("exception"));
        Assertions.assertThrows(Exception.class, () -> {
            tradeService.createOrUpdateTrade(trade);
        });
    }

}