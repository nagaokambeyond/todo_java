package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status {
  @Id
  private Long id;

  @Column(nullable = false)
  private String statusName;
}
