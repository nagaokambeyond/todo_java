package com.example.demo.response;

import lombok.Data;

@Data
public class ToDoListDataResponse {
  private Long id;
  private Long statusId;
  private String statusName;
  private String message;
  private Integer done;
  private String updateDatetime;
}
