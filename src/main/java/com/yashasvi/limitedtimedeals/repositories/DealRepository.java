package com.yashasvi.limitedtimedeals.repositories;

import com.yashasvi.limitedtimedeals.models.Deal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    @Modifying
    @Query("update Deal d set d.quantity = d.quantity - 1 where d.id = :dealId and d.quantity > 0 and d.endTime > current_timestamp")
    @Transactional
    int decrementQuantity(Long dealId);
}
