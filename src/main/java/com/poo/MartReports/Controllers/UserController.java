package com.poo.MartReports.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.MartReports.Exceptions.UserNotFoundException;
import com.poo.MartReports.Models.User;
import com.poo.MartReports.Services.UserServiceInterface;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {
    @Autowired
    private UserServiceInterface userService;

    @Override
    @GetMapping
    public ResponseEntity<String> findAllUsers() {
        return new ResponseEntity<String>((userService.findAllUsers()).toString(), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<String>((userService.getUserById(id)).toString(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User u) {
        User newUser = userService.registerUser(u);
        if(newUser != null) {
            return new ResponseEntity<String>(newUser.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("It was not possible to create the user", HttpStatus.NOT_MODIFIED);
        }
    }
    
    @Override
    @PutMapping
    public ResponseEntity<String> editUser(@RequestBody User newU) {
        try {
            userService.editUser(newU);
            return new ResponseEntity<String>(newU.toString(), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
