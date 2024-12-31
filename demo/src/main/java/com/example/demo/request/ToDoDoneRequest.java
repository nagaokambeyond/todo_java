package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@Value
public class ToDoDoneRequest {
  @NotNull
  private Long id;

  @NotNull
  private Boolean done;
}
