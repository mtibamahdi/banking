package com.mtibaa.banking.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

@Builder
@AllArgsConstructor
public class ExceptionRepresentation {
    private String errorMessage;
    private String errorSource;
    private Set<String> validationErrors;
}
