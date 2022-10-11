package com.example.demo.api;

import com.example.demo.response.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {
  @ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "失敗", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorResponse.class)))
  })
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<Object> handleBindException(BindException bindException) {
    List<ValidationErrorResponse> errors = bindException.getFieldErrors().stream()
      .map(r -> {
        var row = new ValidationErrorResponse();
        row.setFieldName(r.getField());
        row.setMessage(r.getDefaultMessage());
        return row;
      }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
