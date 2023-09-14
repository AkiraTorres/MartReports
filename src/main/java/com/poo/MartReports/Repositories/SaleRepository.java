package com.poo.MartReports.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poo.MartReports.Models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
