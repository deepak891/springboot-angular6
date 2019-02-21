package com.devlabs.rest.webservices.restfulwebservices.controller;

import com.devlabs.rest.webservices.restfulwebservices.model.Todo;
import com.devlabs.rest.webservices.restfulwebservices.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResources {

    @Autowired
    private TodoHardcodedService todoHardcodedService;

    @GetMapping( value = "/Users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoHardcodedService.findAll();
    }

    @GetMapping( value = "/Users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return todoHardcodedService.findById(id);
    }

    @DeleteMapping(value = "/Users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
        Todo todo =todoHardcodedService.deleteById(id);
        if(todo != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/Users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,
                                           @PathVariable long id,
                                           @RequestBody Todo todo) {

        Todo todoUpdated = todoHardcodedService.saveTodo(todo);
        return new ResponseEntity<Todo>(todo,HttpStatus.OK);
    }

    @PostMapping(value = "/Users/{username}/todos")
    public ResponseEntity<Void> updateTodo(@PathVariable String username,
                                           @RequestBody Todo todo) {

        Todo createdTod = todoHardcodedService.saveTodo(todo);
        //Location
        //Get current resource url
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTod.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
