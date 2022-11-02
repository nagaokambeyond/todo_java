package com.example.demo.api;

import com.example.demo.request.ToDoCreateRequest;
import com.example.demo.request.ToDoDoneRequest;
import com.example.demo.request.ToDoListRequest;
import com.example.demo.response.ToDoListResponse;
import com.example.demo.response.ToDoResponse;
import com.example.demo.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "TODO API")
@RequestMapping("/api/v1/todos")
class ToDosRestController {
  /**
   * @see ToDoService .
   */
  private final ToDoService service;

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json"))
  })
  @Operation(summary = "ToDoを`Done`に更新する")
  @PutMapping
  ResponseEntity<Object> done(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "登録内容") @RequestBody @Validated final ToDoDoneRequest request,
    final BindingResult result
  ) throws BindException {
    if (result.hasErrors()) {
      throw new BindException(result);
    }
    service.done(request);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "成功")
  })
  @Operation(summary = "ToDoを削除する")
  @DeleteMapping("{id}")
  ResponseEntity<Object> delete(
    @Parameter(required = true, description = "条件") @PathVariable final Long id
  ) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "成功")
  })
  @Operation(summary = "ToDoを保存する")
  @PostMapping
  ResponseEntity<Object> save(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "登録内容") @RequestBody @Validated final ToDoCreateRequest request,
    final BindingResult result
  ) throws BindException {
    if (result.hasErrors()) {
      throw new BindException(result);
    }
    service.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ToDoResponse.class)))
  })
  @Operation(summary = "ToDoを取得する")
  @GetMapping("{id}")
  ResponseEntity<ToDoResponse> getToDo(@Parameter(required = true, description = "条件") @PathVariable final Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.get(id));
  }

  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ToDoListResponse.class)))
  })
  @Operation(summary = "ToDoListを取得する")
  @GetMapping
  ResponseEntity<ToDoListResponse> getToDoList(
    @Parameter(required = true, description = "条件") @ModelAttribute @Validated final ToDoListRequest condition
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getList(condition));
  }
}
