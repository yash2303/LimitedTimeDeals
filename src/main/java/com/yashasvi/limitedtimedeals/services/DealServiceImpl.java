package com.yashasvi.limitedtimedeals.services;

import com.yashasvi.limitedtimedeals.dtos.ClaimDealResponse;
import com.yashasvi.limitedtimedeals.dtos.CreateDealRequest;
import com.yashasvi.limitedtimedeals.dtos.DealDto;
import com.yashasvi.limitedtimedeals.dtos.UpdateDealRequest;
import com.yashasvi.limitedtimedeals.models.Deal;
import com.yashasvi.limitedtimedeals.repositories.DealRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.yashasvi.limitedtimedeals.services.DealDtoConverter.convertToDeal;
import static com.yashasvi.limitedtimedeals.services.DealDtoConverter.convertToDealDto;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public DealDto createDeal(CreateDealRequest createDealRequest) {
        validateCreateDealRequest(createDealRequest);
        Deal deal = convertToDeal(createDealRequest);

        Deal savedDeal = dealRepository.save(deal);

        return convertToDealDto(savedDeal);
    }

    @Override
    public DealDto endDeal(Long dealId) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new NoSuchElementException("Deal with id " + dealId + " not found"));
        deal.setEndTime(new java.util.Date());
        deal.setQuantity(0);
        return convertToDealDto(dealRepository.save(deal));
    }

    @Override
    public DealDto updateDeal(Long dealId, UpdateDealRequest updateDealRequest) {
        validateUpdateDealRequest(updateDealRequest);
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new NoSuchElementException("Deal with id " + dealId + " not found"));
        if (updateDealRequest.getQuantity() != null) {
            deal.setQuantity(updateDealRequest.getQuantity());
        }
        if (updateDealRequest.getEndTime() != null) {
            deal.setEndTime(updateDealRequest.getEndTime());
        }
        return convertToDealDto(dealRepository.save(deal));
    }

    @Override
    public ClaimDealResponse claimDeal(Long dealId) {
        int updatedRows = dealRepository.decrementQuantity(dealId);
        if (updatedRows == 0) {
            return new ClaimDealResponse(false, "Deal not available");
        }
        return new ClaimDealResponse(true, "Deal claimed successfully");
    }

    private void validateCreateDealRequest(CreateDealRequest createDealRequest) {
        if (createDealRequest.getPrice() <= 0) {
            throw new IllegalArgumentException("Price should be greater than 0");
        }
        if (createDealRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity should be greater than 0");
        }
        if (createDealRequest.getEndTime() != null && createDealRequest.getEndTime().before(new java.util.Date())) {
            throw new IllegalArgumentException("End time should be in future");
        }
    }

    private void validateUpdateDealRequest(UpdateDealRequest updateDealRequest) {
        if (updateDealRequest.getQuantity() != null && updateDealRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity should be greater than 0");
        }
        if (updateDealRequest.getEndTime() != null && updateDealRequest.getEndTime().before(new java.util.Date())) {
            throw new IllegalArgumentException("End time should be in future");
        }
    }
}
