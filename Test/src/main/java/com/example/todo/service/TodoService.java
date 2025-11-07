package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }


    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new com.example.todo.exception.TodoNotFoundException(id));
    }

    public int createTodo(Todo todo) {
        return todoRepository.save(todo);
    }


    public int updateTodo(Long id, Todo todo) {
        if (!todoRepository.findById(id).isPresent()) {
            throw new com.example.todo.exception.TodoNotFoundException(id);
        }
        return todoRepository.update(id, todo);
    }


    public int deleteTodo(Long id) {
        if (!todoRepository.findById(id).isPresent()) {
            throw new com.example.todo.exception.TodoNotFoundException(id);
        }
        return todoRepository.delete(id);
    }
}