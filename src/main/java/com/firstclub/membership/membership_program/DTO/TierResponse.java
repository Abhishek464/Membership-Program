package com.firstclub.membership.membership_program.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TierResponse {

    private Long tierId;
    private String tierName;
    private Integer discountPercent;
    private Boolean freeDelivery;
    private Boolean prioritySupport;
    private Boolean earlyAccess;
}
