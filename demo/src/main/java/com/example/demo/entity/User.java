package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {
  @Id
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;
}
