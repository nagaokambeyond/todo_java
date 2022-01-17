package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}