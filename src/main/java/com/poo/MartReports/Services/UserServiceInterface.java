package com.poo.MartReports.Services;

import java.util.List;

import com.poo.MartReports.Exceptions.UserNotFoundException;
import com.poo.MartReports.Models.User;

public interface UserServiceInterface {

    public List<User> findAllUsers();

    public User getUserById(Long id) throws UserNotFoundException;

    public User registerUser(User u);

    public User editUser(User newU) throws UserNotFoundException;

    public void deleteById(Long id);

}