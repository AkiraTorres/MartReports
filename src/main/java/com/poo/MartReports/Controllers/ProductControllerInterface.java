package com.poo.MartReports.Controllers;

import org.springframework.http.ResponseEntity;

import com.poo.MartReports.Models.Product;

public interface ProductControllerInterface {
    
    public ResponseEntity<String> findAllProducts();

    public ResponseEntity<String> findProductById(Long id);

    public ResponseEntity<String> registerProduct(Product p);

    public ResponseEntity<String> editProduct(Product newP);

    public ResponseEntity<String> deleteProductById(Long id);
}
