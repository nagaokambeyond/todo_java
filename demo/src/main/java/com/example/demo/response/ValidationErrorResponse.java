package com.example.demo.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ValidationErrorResponse {
  private String fieldName;
  private String message;
}
