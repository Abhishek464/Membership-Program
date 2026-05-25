package com.firstclub.membership.membership_program.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserMetrics {
    private Integer orderCount;
    private BigDecimal totalOrderValue;
    private String cohort;
}
