package com.example.demo.response;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import com.example.demo.request.ToDoListRequest;

@Builder
@Value
public class ToDoListResponse {
  private final ToDoListRequest condition;
  private final List<ToDoListDataResponse> data;
}
