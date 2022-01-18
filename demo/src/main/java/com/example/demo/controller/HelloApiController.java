package com.example.demo.controller;

import com.example.demo.response.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {
  @GetMapping("/hello")
  public Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    var aa = new Greeting(1, "hello" + name);

    return aa;
  }
}
