package com.example.demo.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ValidationErrorResponse {
  String fieldName;
  String message;
}
