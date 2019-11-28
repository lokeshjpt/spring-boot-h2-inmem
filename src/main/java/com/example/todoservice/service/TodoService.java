package com.example.todoservice.service;

import com.example.todoservice.controller.TodoController;
import com.example.todoservice.dao.TodoRepository;
import com.example.todoservice.model.Todo;
import com.example.todoservice.util.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @Cacheable(value="todos")
    public Page<Todo> getTodoList(Pageable pageable) {
        logger.debug("TodoService::findAll");
        return todoRepository.findAll(pageable);
    }

    @CacheEvict(value = "todos", allEntries = true)
    public Todo addTodo(Todo todo){
        logger.debug("TodoService::add");
        return todoRepository.save(todo);

    }

    public Todo updateTodo(Todo todo) throws ResourceNotFoundException{
        logger.debug("TodoService::update");
        todoRepository.findById(todo.getId()).orElseThrow(()-> new ResourceNotFoundException("Unable to find todo with id : "+ todo.getId()));
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) throws ResourceNotFoundException{
        logger.debug("TodoService::delete");
        todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Unable to find todo with id : "+ id));
        todoRepository.deleteById(id);
    }


}
