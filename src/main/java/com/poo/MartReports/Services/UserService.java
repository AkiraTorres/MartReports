package com.poo.MartReports.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.MartReports.Models.User;
import com.poo.MartReports.Repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User registerUser(User u) {
        userRepo.save(u);
        return u;
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
