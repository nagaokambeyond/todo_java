package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@Value
public class ToDoCreateRequest {
  @NotNull
  private Long statusId;
  @NotBlank
  @Size(min = 1, max = 10)
  private String message;
}
