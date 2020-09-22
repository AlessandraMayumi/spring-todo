package com.example.todo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private final TodoRepository repository;

    TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/todo")
    List<Todo> all() {
        return repository.findAll();
    }

    @PostMapping("/todo")
    Todo newTodo(@RequestBody Todo newTodo) {
        return repository.save(newTodo);
    }

    // Single item

    @GetMapping("/todo/{id}")
    Todo one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PutMapping("/todo/{id}")
    Todo replaceTodo(@RequestBody Todo newTodo, @PathVariable Long id) {
        return repository.findById(id)
                .map(todo -> {
                    todo.setTitle(newTodo.getTitle());
                    todo.setDone(newTodo.getDone());
                    return repository.save(newTodo);
                })
                .orElseGet(() -> {
                    newTodo.setId(id);
                    return repository.save(newTodo);
                });
    }

    @DeleteMapping("/todo/{id}")
    void deleteTodo(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
