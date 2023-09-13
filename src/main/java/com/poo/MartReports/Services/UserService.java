package com.poo.MartReports.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.MartReports.Exceptions.UserNotFoundException;
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
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepo.findById(id).get();
    }

    @Override
    public User registerUser(User u) {
        userRepo.save(u);
        return u;
    }
    
    @Override
    public User editUser(User newU) throws UserNotFoundException {
        User oldUser = getUserById(newU.getId());
        if (oldUser == null) {
            throw new UserNotFoundException("Id informed is not in the system.");
        } else if (oldUser.equals(newU)) {
            return oldUser; // if the edited user is equal to the old user, no changes are necessary
        } else {
            oldUser.setName(newU.getName());
            oldUser.setEmail(newU.getEmail());
            oldUser.setPassword(newU.getPassword());
            oldUser.setUserType(newU.getUserType());
            
            userRepo.save(oldUser);
        }
        return oldUser;
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
