package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.ToDoRepository;
import com.example.demo.entity.ToDo;
import com.example.demo.response.ToDoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.modelmapper.ModelMapper;

@RestController
public class ToDoController {
    @Autowired
    ToDoRepository repository;

    @GetMapping("/todo")
    public List<ToDoResponse> getToDoList(){
        var aaa = repository.findAll();
        var result = new ArrayList<ToDoResponse>();
        ModelMapper x = new ModelMapper();
        for(var kk : aaa){
            var pp = x.map(kk, ToDoResponse.class);
            /*
            ToDoResponse pp = new ToDoResponse();
            pp.setId(kk.getId());
            pp.setStatus(kk.getStatus());
            pp.setMessage(kk.getMessage());
            */
            result.add(pp);
        }        

        return result;
    }
}
