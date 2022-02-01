package com.example.demo.request;

import lombok.Data;

@Data
public class ToDoListRequest {
  private Integer page;
  private Long statusId;
}
