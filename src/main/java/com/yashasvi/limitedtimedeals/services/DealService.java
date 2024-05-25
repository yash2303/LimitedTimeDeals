package com.yashasvi.limitedtimedeals.services;

import com.yashasvi.limitedtimedeals.dtos.ClaimDealResponse;
import com.yashasvi.limitedtimedeals.dtos.CreateDealRequest;
import com.yashasvi.limitedtimedeals.dtos.DealDto;
import com.yashasvi.limitedtimedeals.dtos.UpdateDealRequest;

public interface DealService {
    DealDto createDeal(CreateDealRequest createDealRequest);

    DealDto endDeal(Long dealId);

    DealDto updateDeal(Long dealId, UpdateDealRequest updateDealRequest);

    ClaimDealResponse claimDeal(Long dealId);
}
