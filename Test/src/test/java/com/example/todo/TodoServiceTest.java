package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTodoById_found() {
        Todo todo = new Todo(1L, "Test", false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        Todo result = todoService.getTodoById(1L);
        assertEquals(todo, result);
    }

    @Test
    void getTodoById_notFound() {
        when(todoRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(com.example.todo.exception.TodoNotFoundException.class, () -> todoService.getTodoById(2L));
    }
}
