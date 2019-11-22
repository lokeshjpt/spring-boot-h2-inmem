package com.example.todoservice.controller;

import com.example.todoservice.model.Todo;
import com.example.todoservice.service.TodoService;
import com.example.todoservice.util.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping()
    public Page<Todo> getTodoList(Pageable pageable){
        return todoService.getTodoList(pageable);
    }

    @PostMapping()
    public @ResponseBody Todo addTodo(@Valid @RequestBody Todo todo){
        return todoService.addTodo(todo);
    }

    @PutMapping()
    public @ResponseBody Todo updateTodo(@Valid @RequestBody Todo todo) throws ResourceNotFoundException {
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) throws ResourceNotFoundException{
        todoService.deleteTodo(id);
    }

}
