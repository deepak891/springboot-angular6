package com.devlabs.rest.webservices.restfulwebservices.helloworld;

import com.devlabs.rest.webservices.restfulwebservices.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {

    @GetMapping( path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping( path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        throw new RuntimeException("Some error has happened");
        //return new HelloWorldBean("Hello World");
    }

    @GetMapping( path = "/hello-world/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s",name ));
    }




}
