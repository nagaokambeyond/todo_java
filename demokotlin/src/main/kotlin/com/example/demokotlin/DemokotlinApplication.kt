package com.example.demokotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemokotlinApplication

  fun main(args: Array<String>) {
    runApplication<DemokotlinApplication>(*args)
  }
