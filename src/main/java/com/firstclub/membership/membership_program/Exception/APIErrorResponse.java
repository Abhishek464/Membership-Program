package com.firstclub.membership.membership_program.Exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class APIErrorResponse {

    private String message;
    private Integer status;
    private LocalDateTime timestamp;
}
