package com.yashasvi.limitedtimedeals.services;

import com.yashasvi.limitedtimedeals.dtos.CreateDealRequest;
import com.yashasvi.limitedtimedeals.dtos.UpdateDealRequest;
import com.yashasvi.limitedtimedeals.models.Deal;
import com.yashasvi.limitedtimedeals.repositories.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    void createDeal() {
        CreateDealRequest request = new CreateDealRequest();
        Date endTime = new Date(System.currentTimeMillis() + 86400000L);
        request.setPrice(100.0);
        request.setQuantity(10);
        request.setEndTime(endTime);

        Deal deal = new Deal();
        deal.setPrice(100.0);
        deal.setQuantity(10);
        deal.setEndTime(endTime);

        when(dealRepository.save(any(Deal.class))).thenReturn(deal);

        var result = dealService.createDeal(request);

        assertEquals(deal.getPrice(), result.getPrice());
        assertEquals(deal.getQuantity(), result.getQuantity());
        assertEquals(deal.getEndTime(), result.getEndTime());
    }

    @Test
    void endDeal() {
        Deal deal = new Deal();
        deal.setId(1L);
        Deal updatedDeal = new Deal();
        updatedDeal.setId(1L);
        updatedDeal.setQuantity(0);
        when(dealRepository.findById(1L)).thenReturn(Optional.of(deal));
        when(dealRepository.save(any(Deal.class))).thenReturn(updatedDeal);

        var result = dealService.endDeal(deal.getId());

        assertEquals(deal.getId(), result.getId());
    }

    @Test
    void updateDeal() {
        Date endTime = new Date(System.currentTimeMillis() + 86400000L);
        UpdateDealRequest request = new UpdateDealRequest();
        request.setQuantity(20);
        request.setEndTime(endTime);

        Deal deal = new Deal();
        deal.setId(1L);

        Deal updatedDeal = new Deal();
        updatedDeal.setId(1L);
        deal.setQuantity(20);
        deal.setEndTime(endTime);

        when(dealRepository.findById(1L)).thenReturn(Optional.of(deal));
        when(dealRepository.save(any(Deal.class))).thenReturn(updatedDeal);
        var result = dealService.updateDeal(deal.getId(), request);

        assertEquals(updatedDeal.getQuantity(), result.getQuantity());
        assertEquals(updatedDeal.getEndTime(), result.getEndTime());
    }

    @Test
    void claimDeal() {
        when(dealRepository.decrementQuantity(anyLong())).thenReturn(1);

        var result = dealService.claimDeal(1L);

        assertTrue(result.isSuccess());
        assertEquals("Deal claimed successfully", result.getMessage());
    }
}