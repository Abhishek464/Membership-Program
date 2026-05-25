package com.firstclub.membership.membership_program.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DowngradeRequest {

    @NotNull
    private Long userId;
}
