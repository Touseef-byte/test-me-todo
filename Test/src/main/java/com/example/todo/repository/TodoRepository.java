package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Todo> rowMapper = new RowMapper<Todo>() {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Todo(
                rs.getLong("id"),
                rs.getString("description"),
                rs.getBoolean("completed")
            );
        }
    };

    public List<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todos", rowMapper);
    }

    public Optional<Todo> findById(Long id) {
        List<Todo> results = jdbcTemplate.query("SELECT * FROM todos WHERE id = ?", rowMapper, id);
        return results.stream().findFirst();
    }

    public int save(Todo todo) {
        return jdbcTemplate.update(
            "INSERT INTO todos (description, completed) VALUES (?, ?)",
            todo.getDescription(), todo.isCompleted()
        );
    }

    public int update(Long id, Todo todo) {
        return jdbcTemplate.update(
            "UPDATE todos SET description = ?, completed = ? WHERE id = ?",
            todo.getDescription(), todo.isCompleted(), id
        );
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM todos WHERE id = ?", id);
    }
}