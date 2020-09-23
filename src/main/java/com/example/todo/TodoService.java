package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    public Todo update(Long id, Todo newTodo){
        return repository.findById(id)
                .map(todo -> {
                    todo.setTitle(newTodo.getTitle());
                    todo.setDone(newTodo.getDone());
                    return repository.save(newTodo);
                })
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
