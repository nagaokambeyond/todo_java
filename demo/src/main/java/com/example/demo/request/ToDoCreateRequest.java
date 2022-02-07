package com.example.demo.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ToDoCreateRequest {
  @NotNull
  private Long statusId;
  @NotBlank
  @Size(min = 1, max = 10)
  private String message;
}
