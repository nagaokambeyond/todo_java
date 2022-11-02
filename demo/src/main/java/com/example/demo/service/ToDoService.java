package com.example.demo.service;

import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.request.ToDoListRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;

public interface ToDoService {
  /**
   * delete.
   * @param id 対象id
   */
  void delete(Long id);

  /**
   * done.
   * @param request ToDoDoneRequest
   */
  void done(ToDoDoneRequest request);

  /**
   * save.
   * @param request ToDoCreateRequest
   */
  void save(ToDoCreateRequest request);

  /**
   * get.
   * @param id 対象id
   * @return ToDoResponse
   */
  ToDoResponse get(Long id);

  /**
   * get list.
   * @param condition 条件
   * @return ToDoListResponse
   */
  ToDoListResponse getList(ToDoListRequest condition);
}
