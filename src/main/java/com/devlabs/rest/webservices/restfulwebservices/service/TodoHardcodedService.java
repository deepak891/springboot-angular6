package com.devlabs.rest.webservices.restfulwebservices.service;

import com.devlabs.rest.webservices.restfulwebservices.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardcodedService {
    private static List<Todo> todos = new ArrayList<>();
    private static long idCounter = 0;

    static {
        todos.add(new Todo(++idCounter, "devlabs", "Learn python", new Date(), false));
        todos.add(new Todo(++idCounter, "devlabs", "Learn micro service", new Date(), false));
        todos.add(new Todo(++idCounter, "devlabs", "Learn angular and react", new Date(), false));
        todos.add(new Todo(++idCounter, "devlabs", "Learn shell", new Date(), false));
    }

    public List<Todo> findAll(){
        return todos;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        todos.remove(todo);
        return todo;
    }

    public Todo saveTodo(Todo todo) {
        if(todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        }else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

    public Todo findById(long id) {
        return todos.stream().filter( s -> s.getId() == id).findAny().orElse(null);
    }
}
