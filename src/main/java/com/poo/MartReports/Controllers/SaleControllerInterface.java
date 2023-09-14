package com.poo.MartReports.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.poo.MartReports.Models.Sale;

public interface SaleControllerInterface {

    public ResponseEntity<String> findAllSales();

    public ResponseEntity<String> getSaleById(Long id);

    public ResponseEntity<String> registerSale(Sale s, List<Long> produtoIds, Long storeId);

    public ResponseEntity<String> editSale(Sale newS);

    public ResponseEntity<String> deleteSaleById(Long id);
}
