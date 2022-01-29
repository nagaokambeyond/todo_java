package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "todo")
public class ToDo {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long statusId;

  @Column(nullable = false)
  private String message;

  @Column(nullable = false)
  private Integer done;

  @Column(nullable = false)
  private Long createUserId;

  @Column(nullable = false)
  private String updateDatetime;
}
