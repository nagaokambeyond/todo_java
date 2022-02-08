package com.example.demo.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ToDoDoneRequest {
  @NotNull
  private Long id;

  @NotNull
  private Boolean done;
}
