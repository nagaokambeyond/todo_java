package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "status")
public class Status {
  @Id
  private Long id;

  @Column(nullable = false)
  private String statusName;
}
