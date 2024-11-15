package com.example.domain.controller;

import com.example.domain.model.User;
import com.example.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById (@PathVariable Long id){
        // Aqui é utilizado o tipo var pois o findById pode retornar uma NoSuchElementException
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User userToCreate){
        // Ídem para IllegalArgumentException
        var userCreated = userService.create(userToCreate);

        // Pegar depois explicação de como isso aqui funciona
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        // ResponseEntity.created é a resposta 201 da EndPoint
        return ResponseEntity.created(location).body(userCreated);
    }
}
