package com.example.demo.service;

import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.request.ToDoListRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;

public interface ToDoService {
  void delete(Long id);

  void done(ToDoDoneRequest request);

  void save(ToDoCreateRequest request);

  ToDoResponse get(Long id);

  ToDoListResponse getList(ToDoListRequest condition);
}
