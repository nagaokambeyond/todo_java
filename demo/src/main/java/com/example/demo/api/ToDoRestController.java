package com.example.demo.api;


import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.ToDoRepository;
import com.example.demo.response.ToDoResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoRestController {
  @Autowired
  ToDoRepository repository;

  @GetMapping("/todo")
  public List<ToDoResponse> getToDoList() {
    var result = new ArrayList<ToDoResponse>();
    var map = new ModelMapper();
    for (var row : repository.findAll()) {
      var todo = map.map(row, ToDoResponse.class);
      result.add(todo);
    }

    return result;
  }
}
