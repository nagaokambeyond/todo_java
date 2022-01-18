package com.example.demo.response;

import lombok.Data;

@Data
public class ToDoResponse {
  private Long id;
  private Long status;
  private String message;
}
