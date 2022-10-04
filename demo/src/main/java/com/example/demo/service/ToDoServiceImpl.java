package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;
import com.example.demo.response.ToDoListDataResponse;
import com.example.demo.entity.Status;
import com.example.demo.entity.ToDo;
import com.example.demo.request.ToDoListRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ToDoServiceImpl implements ToDoService {
  @Autowired
  protected ToDoRepository todo;

  @Autowired
  protected StatusRepository status;

  @Override
  public void delete(Long id) {
    todo.deleteById(id);
  }

  @Transactional
  @Override
  public void done(ToDoDoneRequest request) {
    var entity = todo.getReferenceById(request.getId());
    if(request.getDone()){
      entity.setDone(1);
    }else{
      entity.setDone(0);
    }
    final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
    todo.save(entity);
  }

  @Transactional
  @Override
  public void save(ToDoCreateRequest request) {
    final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    var entity = new ToDo();
    entity.setStatusId(request.getStatusId());
    entity.setMessage(request.getMessage());
    entity.setCreateUserId(1L);
    entity.setDone(0);
    entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
    todo.save(entity);
  }

  @Override
  public ToDoResponse get(Long id){
    final Map<Long, Status> map = status.findAll()
      .stream()
      .collect(
          Collectors.toMap(Status::getId, r -> r)
      )
    ;

    final var row = todo.getReferenceById(id);
    var mod = new ModelMapper();
    var result = mod.map(row, ToDoResponse.class);
    result.setStatusName(map.get(result.getStatusId()).getStatusName());
    return result;
  }

  @Override
  public ToDoListResponse getList(ToDoListRequest condition) {
    var mod = new ModelMapper();
    final Map<Long, Status> map = status.findAll()
      .stream()
      .collect(
          Collectors.toMap(Status::getId, r -> r)
      )
    ;

    final List<ToDoListDataResponse> data = todo.findAll()
      .stream()
      .map(r -> {
        final var row = mod.map(r, ToDoListDataResponse.class);
        row.setStatusName(map.get(row.getStatusId()).getStatusName());

        return row;
      })
      .collect(Collectors.toList())
    ;

    var result = new ToDoListResponse();
    result.setData(data);
    result.setCondition(condition);
    return result;
  }
}
