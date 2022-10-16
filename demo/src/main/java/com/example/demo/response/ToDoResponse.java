package com.example.demo.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ToDoResponse {
  Long id;
  Long statusId;
  String statusName;
  String message;
  Integer done;
  String updateDatetime;
}
