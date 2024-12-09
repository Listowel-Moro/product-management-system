package com.listo.pms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private HttpStatus statusCode;
    private LocalDate timestamp;
}
