package com.yashasvi.limitedtimedeals.services;

import com.yashasvi.limitedtimedeals.dtos.CreateDealRequest;
import com.yashasvi.limitedtimedeals.dtos.DealDto;
import com.yashasvi.limitedtimedeals.models.Deal;

import java.util.Date;

public class DealDtoConverter {
    private DealDtoConverter() {
    }

    static DealDto convertToDealDto(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setPrice(deal.getPrice());
        dealDto.setEndTime(deal.getEndTime());
        dealDto.setQuantity(deal.getQuantity());
        return dealDto;
    }

    static Deal convertToDeal(CreateDealRequest createDealRequest) {
        Deal deal = new Deal();
        deal.setPrice(createDealRequest.getPrice());
        // if end time is null set it to 100 yrs from now
        if (createDealRequest.getEndTime() == null) {
            deal.setEndTime(new Date(System.currentTimeMillis() + 3155695200000L));
        } else {
            deal.setEndTime(createDealRequest.getEndTime());
        }
        deal.setQuantity(createDealRequest.getQuantity());
        return deal;
    }
}
