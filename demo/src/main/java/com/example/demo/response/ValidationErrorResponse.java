package com.example.demo.response;

import lombok.Data;

@Data
public class ValidationErrorResponse {
  private String fieldName;
  private String message;
}
