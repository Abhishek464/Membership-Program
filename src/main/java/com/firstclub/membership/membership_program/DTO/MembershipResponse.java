package com.firstclub.membership.membership_program.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MembershipResponse {

    private Long membershipId;
    private Long userId;
    private String planName;
    private String tierName;
    private String status;
    private LocalDate startDate;
    private LocalDate expiryDate;
}
