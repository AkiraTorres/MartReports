package com.poo.MartReports.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.MartReports.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
