package com.poo.MartReports.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.MartReports.Models.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    
}
