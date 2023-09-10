package com.poo.MartReports.Controllers;

import org.springframework.http.ResponseEntity;

import com.poo.MartReports.Models.User;

public interface UserControllerInterface {

    public ResponseEntity<String> findAllUsers();

    public ResponseEntity<String> getUserById(Long id);

    public ResponseEntity<String> registerUser(User u);

    public ResponseEntity<String> editUser(User newU);

    public ResponseEntity<String> deleteUserById(Long id);

}