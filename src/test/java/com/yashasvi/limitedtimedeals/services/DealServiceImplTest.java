package com.yashasvi.limitedtimedeals.services;


import com.yashasvi.limitedtimedeals.repositories.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class DealServiceImplTest {
    @Mock
    private DealRepository dealRepository;

    private DealServiceImpl dealService;

    @BeforeEach
    void setUp() {
        dealService = new DealServiceImpl(dealRepository);
    }

    @Test
    void createDeal() {

    }

    @Test
    void endDeal() {

    }

    @Test
    void updateDeal() {
    }

    @Test
    void claimDeal() {
    }

}