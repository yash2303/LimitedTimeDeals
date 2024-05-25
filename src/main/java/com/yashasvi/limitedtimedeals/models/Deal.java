package com.yashasvi.limitedtimedeals.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Deal extends BaseEntity {
    private Double price;
    private Date endTime;
    private int quantity;
}
