package com.example.demo.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ToDoListDataResponse {
  private Long id;
  private Long statusId;
  private String statusName;
  private String message;
  private Integer done;
  private String updateDatetime;
}
