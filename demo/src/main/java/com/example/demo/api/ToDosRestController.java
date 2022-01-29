package com.example.demo.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoListDataResponse;
import com.example.demo.entity.Status;
import com.example.demo.entity.ToDo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
@RestController
@RequestMapping("/api/v1/todos")
public class ToDosRestController {
  @Autowired
  protected ToDoRepository todo;

  @Autowired
  protected StatusRepository status;

  @PostMapping
  public void save() {
    final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    var entity = new ToDo();
    entity.setMessage("test");
    entity.setCreateUserId(1L);
    entity.setDone(0);
    entity.setStatusId(0L);
    entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
    todo.save(entity);
  }

  @GetMapping
  public ToDoListResponse getToDoList(
      @RequestParam(required = false) Integer page
    ) {
    ModelMapper mod = new ModelMapper();
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
    result.setPage(page);
    return result;
  }
}
