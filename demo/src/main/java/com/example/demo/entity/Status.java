package com.example.demo.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "status")
public class Status {
  @Id
  private Long id;

  @Column(nullable = false)
  private String statusName;
}
