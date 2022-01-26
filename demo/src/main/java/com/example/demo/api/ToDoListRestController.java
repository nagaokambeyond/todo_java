package com.example.demo.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.entity.Status;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoListRestController {
  @Autowired
  ToDoRepository todo;

  @Autowired
  StatusRepository status;

  @GetMapping("/todolist")
  public List<ToDoListResponse> getToDoList() {
    var mod = new ModelMapper();

    final Map<Long, Status> map = status.findAll().stream()
      .collect(
          Collectors.toMap(Status::getId, r->r)
      )
    ;

    final List <ToDoListResponse> result = todo.findAll()
      .stream()
      .map(r -> {
        ToDoListResponse row = mod.map(r, ToDoListResponse.class);
        row.setStatusName(map.get(row.getStatusId()).getStatusName());

        return row;
      })
      .collect(Collectors.toList())
    ;

    return result;
  }
}
