package com.yashasvi.limitedtimedeals.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yashasvi.limitedtimedeals.models.Deal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Deal}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealDto implements Serializable {
    private Long id;
    private Double price;
    private Date endTime;
    private int quantity;
}