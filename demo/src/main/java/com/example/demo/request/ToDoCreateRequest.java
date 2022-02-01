package com.example.demo.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ToDoCreateRequest {
  @NotNull
  private Long statusId;
  @NotBlank
  private String message;
}
