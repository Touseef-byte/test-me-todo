package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_returnsTodos() {
        Todo todo1 = new Todo(1L, "Test1", false);
        Todo todo2 = new Todo(2L, "Test2", true);
        List<Todo> todos = Arrays.asList(todo1, todo2);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(todos);
        List<Todo> result = todoRepository.findAll();
        assertEquals(2, result.size());
    }
}
