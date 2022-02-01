package com.example.demo.response;

import java.util.List;
import lombok.Data;
import com.example.demo.request.ToDoListRequest;
@Data
public class ToDoListResponse {
  private ToDoListRequest condition;
  private List<ToDoListDataResponse> data;
}
