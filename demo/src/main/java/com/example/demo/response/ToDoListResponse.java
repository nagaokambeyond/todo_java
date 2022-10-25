package com.example.demo.response;

import com.example.demo.request.ToDoListRequest;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ToDoListResponse {
  ToDoListRequest condition;
  List<ToDoListDataResponse> data;
}
