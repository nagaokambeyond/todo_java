package com.example.demo.api;

import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;
import com.example.demo.response.ValidationErrorResponse;
import com.example.demo.service.ToDoService;
import com.example.demo.request.ToDoListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDosRestController {

  @Autowired
  protected ToDoService service;

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleBindException(BindException bindException, Locale locale) {
    List<ValidationErrorResponse> errors = bindException.getFieldErrors().stream()
      .map(r -> {
        var row = new ValidationErrorResponse();
        row.setFieldName(r.getField());
        row.setMessage(r.getDefaultMessage());
        return row;
      }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

  @PutMapping
  public ResponseEntity<Object> done(
    @RequestBody @Validated ToDoDoneRequest request,
    BindingResult result
  ) throws BindException {
    if (result.hasErrors()) {
      throw new BindException(result);
    }
    service.done(request);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> delete(
    @PathVariable Long id
  ) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @PostMapping
  public ResponseEntity<Object> save(
    @RequestBody @Validated ToDoCreateRequest request,
    BindingResult result
  ) throws BindException {
    if (result.hasErrors()) {
      throw new BindException(result);
    }
    service.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping("{id}")
  public ResponseEntity<ToDoResponse> getToDo(@PathVariable Long id){
    return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
  }

  @GetMapping
  public ResponseEntity<ToDoListResponse> getToDoList(ToDoListRequest condition) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getList(condition));
  }
}
