package com.firstclub.membership.membership_program.DTO;

import com.firstclub.membership.membership_program.Enum.MembershipStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscribeRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long planId;

    @NotNull
    private Long tierId;

}
