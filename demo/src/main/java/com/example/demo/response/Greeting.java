package com.example.demo.response;

public class Greeting {
  private Integer id;
  private String message;

  public Greeting(Integer _id, String _message) {
    this.id = _id;
    this.message = _message;
  }

  public Integer getId() {
    return this.id;
  }

  public String getMessage() {
    return this.message;
  }
}
