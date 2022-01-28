package com.example.demo.response;

import java.util.List;
import lombok.Data;

@Data
public class ToDoListResponse {
  private Integer page;
  private List<ToDoListDataResponse> data;
}
