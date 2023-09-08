package com.poo.MartReports.Services;

import java.util.List;

import com.poo.MartReports.Models.User;

public interface UserServiceInterface {

    public List<User> findAllUsers();

    public User getUserById(Long id);

    public User registerUser(User u);

    public void deleteById(Long id);

}