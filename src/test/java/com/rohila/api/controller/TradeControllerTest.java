package com.rohila.api.controller;

import com.rohila.api.bean.Request;
import com.rohila.api.bean.Resource;
import com.rohila.api.dto.Trade;
import com.rohila.api.response.Response;
import com.rohila.api.service.TradeService;
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
public class TradeControllerTest {

    @InjectMocks
    TradeController tradeController;

    @Mock
    TradeService tradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateTrade() {
        Request request = new Request();
        Trade trade = new Trade();
        trade.setTradeId(new Long(1));
        trade.setBookId("b2");
        trade.setCounterPartyId("CP1");
        trade.setMaturityDate(LocalDate.now());
        request.setData(trade);
        Mockito.when(tradeService.createOrUpdateTrade(Mockito.any(Trade.class))).thenReturn(Response.assemble().build(HttpStatus.CREATED, new Resource<Trade>("id", "trades", trade)));
        Response response = tradeController.createOrUpdateTrade(request);
        Assert.assertNotNull(response);
    }

    @Test
    void testCreateTradeWhenError() {
        Request request = new Request();
        Trade trade = new Trade();
        trade.setTradeId(new Long(1));
        trade.setBookId("b2");
        trade.setCounterPartyId("CP1");
        trade.setMaturityDate(LocalDate.now());
        request.setData(trade);
        Mockito.when(tradeService.createOrUpdateTrade(Mockito.any(Trade.class))).thenThrow(new IllegalStateException("exception"));
        Assertions.assertThrows(Exception.class, () -> {
            tradeController.createOrUpdateTrade(request);
        });
    }

}