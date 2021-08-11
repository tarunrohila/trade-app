package com.rohila.api.gateway;

import com.rohila.api.bean.Request;
import com.rohila.api.bean.Resource;
import com.rohila.api.dto.Trade;
import com.rohila.api.gateway.impl.TradeGatewayImpl;
import com.rohila.api.repository.TradeRepository;
import com.rohila.api.repository.domain.TradeDetails;
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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeGatewayTest {

    @InjectMocks
    TradeGatewayImpl tradeGateway;

    @Mock
    TradeRepository tradeRepository;

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
        Mockito.when(tradeRepository.save(Mockito.any(TradeDetails.class))).thenReturn(tradeDetails);
        TradeDetails response = tradeGateway.createOrUpdateTrade(tradeDetails);
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
        Mockito.when(tradeRepository.save(Mockito.any(TradeDetails.class))).thenThrow(new IllegalStateException("exception"));
        Assertions.assertThrows(Exception.class, () -> {
            tradeGateway.createOrUpdateTrade(tradeDetails);
        });
    }
}