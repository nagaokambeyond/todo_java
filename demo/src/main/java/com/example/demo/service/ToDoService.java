package com.example.demo.service;

import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;
import com.example.demo.request.ToDoListRequest;

public interface ToDoService {
  void delete(Long id);

  void save(ToDoCreateRequest form);

  ToDoResponse get(Long id);

  ToDoListResponse getList(ToDoListRequest condition);
}
