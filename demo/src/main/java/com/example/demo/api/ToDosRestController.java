package com.example.demo.api;

import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.service.ToDoService;
import com.example.demo.request.ToDoListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDosRestController {

  @Autowired
  protected ToDoService service;

  @DeleteMapping("{id}")
  ResponseEntity<Object> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @PostMapping
  public ResponseEntity<Object> save(
      @RequestBody @Validated ToDoCreateRequest request,
      BindingResult result) {
    if (result.hasErrors()) {
      List<String> errors = result.getAllErrors().stream()
          .map(r -> r.getDefaultMessage())
          .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    service.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping
  public ResponseEntity<ToDoListResponse> getToDoList(ToDoListRequest condition) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getList(condition));
  }
}
