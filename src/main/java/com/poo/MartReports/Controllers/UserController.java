package com.poo.MartReports.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.MartReports.Models.User;
import com.poo.MartReports.Services.UserServiceInterface;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerInterface {
    @Autowired
    private UserServiceInterface userService;

    @Override
    @GetMapping
    public ResponseEntity findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity registerUser(@RequestBody User u) {
        User newUser = userService.registerUser(u);
        if(newUser != null) {
            return ResponseEntity.ok(newUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
