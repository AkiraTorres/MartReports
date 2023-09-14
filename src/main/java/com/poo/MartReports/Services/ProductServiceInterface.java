package com.poo.MartReports.Services;

import java.util.List;

import com.poo.MartReports.Exceptions.ProductNotFoundException;
import com.poo.MartReports.Models.Product;

public interface ProductServiceInterface {
    
    public List<Product> findAllProducts();

    public Product getProductById(Long id) throws ProductNotFoundException;

    public List<Product> getProductsById(List<Long> ids);

    public Product registerProduct(Product p);

    public Product editProduct(Product newP) throws ProductNotFoundException;

    public void deleteProductById(Long id);
}
