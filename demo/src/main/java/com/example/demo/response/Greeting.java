package com.example.demo.response;

import lombok.Value;

@Value
public class Greeting {
  private final Integer id;
  private final String message;
}
