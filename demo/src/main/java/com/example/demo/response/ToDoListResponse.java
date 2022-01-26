package com.example.demo.response;

import lombok.Data;

@Data
public class ToDoListResponse {
  private Long id;
  private Long statusId;
  private String statusName;
  private String message;
  private Integer done;
  private String updateDatetime;
}
