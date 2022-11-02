package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ToDoListRequest {
  private Integer page;
  private Long statusId;
}
