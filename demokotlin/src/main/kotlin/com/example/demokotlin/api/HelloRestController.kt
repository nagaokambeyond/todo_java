package com.example.demokotlin.api

import com.example.demokotlin.response.Greeting
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping


@RestController
class HelloRestController {
  @GetMapping("/hello")
  fun hello(@RequestParam(value="name", defaultValue = "world")name : String):Greeting {
    val xx = Greeting(1, name)
    return xx
  }
}
