package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
//    @Autowired
    private TodoService service;

    TodoController(TodoService service) {
        this.service = service;
    }

    // Aggregate root

    @GetMapping("/todo")
    List<Todo> all() {
        return service.findAll();
    }

    @PostMapping("/todo")
    Todo newTodo(@RequestBody Todo newTodo) {
        return service.create(newTodo);
    }

    // Single item

    @GetMapping("/todo/{id}")
    Todo one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/todo/{id}")
    Todo replaceTodo(@PathVariable Long id, @RequestBody Todo newTodo) {
        return service.update(id, newTodo);
    }

    @DeleteMapping("/todo/{id}")
    void deleteTodo(@PathVariable Long id) {
        service.deleteById(id);
    }
}
