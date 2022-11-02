package com.example.demo.api;

import com.example.demo.response.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
  @GetMapping("/hello")
  public Greeting hello(
    @RequestParam(value = "name", defaultValue = "World") final String name
  ) {
    return new Greeting(1, "hello" + name);
  }
}
