# Todo List API (Spring Boot)

backend API for managing todo items using Java Spring Boot and SQLite.

## Features
- Create, read, update, and delete todos
- RESTful endpoints
- SQLite database persistence

## Endpoints
- `GET /todos` - List all todos
- `GET /todos/{id}` - Get a todo by ID
- `POST /todos` - Create a new todo
- `PATCH /todos/{id}` - Update a todo
- `DELETE /todos/{id}` - Delete a todo

## Getting Started
1. Build the project:
   ```sh
   mvn clean package
   ```
2. Run the app:
   ```sh
   mvn spring-boot:run
   ```
3. The API will be available at `http://localhost:8080/todos`

## Docker
To run in Docker:
```sh
docker build -t todo-api .
docker run -p 8080:8080 todo-api
```

## Example Todo JSON
```json
{
  "description": "Sample Task",
  "completed": false
}
```
