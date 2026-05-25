package com.firstclub.membership.membership_program.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PlanResponse {

    private Long planId;
    private String planName;
    private Integer durationDays;
    private BigDecimal price;
    private List<TierResponse> tiers;
}
