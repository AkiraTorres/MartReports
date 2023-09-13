package com.poo.MartReports.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.MartReports.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
