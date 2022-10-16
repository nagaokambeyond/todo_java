package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Value
public class ToDoCreateRequest {
  @NotNull
  Long statusId;
  @NotBlank
  @Size(min = 1, max = 10)
  String message;
}
