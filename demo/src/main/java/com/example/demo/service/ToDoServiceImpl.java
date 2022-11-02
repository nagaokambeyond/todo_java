package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.entity.ToDo;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.ToDoRepository;
import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.request.ToDoListRequest;
import com.example.demo.response.ToDoListDataResponse;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {
  /**
   * @see ToDoRepository .
   */
  private final ToDoRepository todo;

  /**
   * @see StatusRepository .
   */
  private final StatusRepository status;

  @Override
  public void delete(final Long id) {
    todo.deleteById(id);
  }

  @Transactional
  @Override
  public void done(final ToDoDoneRequest request) {
    var entity = todo.getReferenceById(request.getId());
    if (request.getDone()) {
      entity.setDone(1);
    } else {
      entity.setDone(0);
    }
    final var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    entity.setUpdateDatetime(LocalDateTime.now().format(dtf));
    todo.save(entity);
  }

  @Transactional
  @Override
  public void save(final ToDoCreateRequest request) {
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
  public ToDoResponse get(final Long id) {
    final Map<Long, Status> map = status.findAll()
      .stream()
      .collect(
          Collectors.toMap(Status::getId, r -> r)
      );

    try {
      final var row = todo.getReferenceById(id);
      return ToDoResponse.builder()
        .id(row.getId())
        .statusId(row.getStatusId())
        .statusName(map.get(row.getStatusId()).getStatusName())
        .message(row.getMessage())
        .done(row.getDone())
        .updateDatetime(row.getUpdateDatetime())
        .build();
    } catch (EntityNotFoundException ex) {
      return null;
    }
  }

  @Override
  public ToDoListResponse getList(final ToDoListRequest condition) {
    final Map<Long, Status> map = status.findAll()
      .stream()
      .collect(
          Collectors.toMap(Status::getId, r -> r)
      );

    final List<ToDoListDataResponse> data = todo.findAll()
      .stream()
      .map(r -> ToDoListDataResponse.builder()
        .id(r.getId())
        .statusId(r.getStatusId())
        .statusName(map.get(r.getStatusId()).getStatusName())
        .message(r.getMessage())
        .done(r.getDone())
        .updateDatetime(r.getUpdateDatetime())
        .build())
      .collect(Collectors.toList());

    return ToDoListResponse.builder()
      .condition(condition)
      .data(data)
      .build();
  }
}
