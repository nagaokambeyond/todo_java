package com.example.demo.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoListDataResponse;
import com.example.demo.entity.Status;
import com.example.demo.entity.ToDo;
import com.example.demo.request.ToDoListRequest;
import org.modelmapper.ModelMapper;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RestController
@RequestMapping("/api/v1/todos")
public class ToDosRestController {
  @Autowired
  protected ToDoRepository todo;

  @Autowired
  protected StatusRepository status;

  @DeleteMapping("{id}")
  ResponseEntity<Object> delete(@PathVariable Long id) {
    todo.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @PostMapping
  public ResponseEntity<Object> save(
      @RequestBody @Validated ToDoCreateRequest form,
      BindingResult result
  ) {
    if (result.hasErrors()){
      List<String> errors = result.getAllErrors().stream()
        .map(r-> r.getDefaultMessage())
        .collect(Collectors.toList()
      );
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    var mod = new ModelMapper();
    var entity = mod.map(form, ToDo.class);;
    entity.setCreateUserId(1L);
    entity.setDone(0);
    entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
    todo.save(entity);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping
  public ResponseEntity<ToDoListResponse> getToDoList(ToDoListRequest condition) {
    var mod = new ModelMapper();
    final Map<Long, Status> map = status.findAll()
      .stream()
      .collect(
          Collectors.toMap(Status::getId, r->r)
      )
    ;

    final List <ToDoListDataResponse> data = todo.findAll()
      .stream()
      .map(r -> {
        final ToDoListDataResponse row = mod.map(r, ToDoListDataResponse.class);
        row.setStatusName(map.get(row.getStatusId()).getStatusName());

        return row;
      })
      .collect(Collectors.toList())
    ;

    var result = new ToDoListResponse();
    result.setData(data);
    result.setCondition(condition);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
