package com.poo.MartReports.Controllers;

import org.springframework.http.ResponseEntity;

import com.poo.MartReports.Models.User;

public interface UserControllerInterface {

    public ResponseEntity findAllUsers();

    public ResponseEntity getUserById(Long id);

    public ResponseEntity registerUser(User u);

    public ResponseEntity deleteUserById(Long id);

}