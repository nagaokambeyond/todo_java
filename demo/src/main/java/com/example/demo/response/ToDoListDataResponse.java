package com.example.demo.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ToDoListDataResponse {
  private final Long id;
  private final Long statusId;
  private final String statusName;
  private final String message;
  private final Integer done;
  private final String updateDatetime;
}
