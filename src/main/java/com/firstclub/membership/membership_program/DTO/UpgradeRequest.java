package com.firstclub.membership.membership_program.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpgradeRequest {

    @NotNull
    private Long userId;
}
