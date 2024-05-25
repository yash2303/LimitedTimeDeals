package com.yashasvi.limitedtimedeals.controllers;

import com.yashasvi.limitedtimedeals.dtos.ClaimDealResponse;
import com.yashasvi.limitedtimedeals.dtos.CreateDealRequest;
import com.yashasvi.limitedtimedeals.dtos.DealDto;
import com.yashasvi.limitedtimedeals.dtos.UpdateDealRequest;
import com.yashasvi.limitedtimedeals.services.DealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    /**
     * Create a deal with the price, end time(optional)
     * and number of items to be sold as part of the deal.
     */
    @PostMapping
    public ResponseEntity<DealDto> createDeal(@RequestBody CreateDealRequest createDealRequest) {
        return ResponseEntity.ok(dealService.createDeal(createDealRequest));
    }

    /**
     * End a deal.
     */
    @DeleteMapping("/{dealId}")
    public ResponseEntity<DealDto> endDeal(@PathVariable Long dealId) {
        return ResponseEntity.ok(dealService.endDeal(dealId));
    }

    /**
     * Update a deal to increase the number of items or end-time.
     */
    @PostMapping("/{dealId}")
    public ResponseEntity<DealDto> updateDeal(@PathVariable Long dealId, @RequestBody UpdateDealRequest updateDealRequest) {
        return ResponseEntity.ok(dealService.updateDeal(dealId, updateDealRequest));
    }

    /**
     * Claim a deal (Basically, creating an order. No need to take care of item price or payment).
     */
    @PostMapping("/{dealId}/claim")
    public ResponseEntity<ClaimDealResponse> claimDeal(@PathVariable Long dealId) {
        return ResponseEntity.ok(dealService.claimDeal(dealId));
    }
}
