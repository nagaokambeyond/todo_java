package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Value
public class ToDoDoneRequest {
  @NotNull
  Long id;

  @NotNull
  Boolean done;
}
