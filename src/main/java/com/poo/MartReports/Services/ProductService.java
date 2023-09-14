package com.poo.MartReports.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.MartReports.Exceptions.ProductNotFoundException;
import com.poo.MartReports.Models.Product;
import com.poo.MartReports.Repositories.ProductRepository;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Product p = productRepo.findById(id).get();
        if (p == null) {
            throw new ProductNotFoundException("Id informed is not in system");
        }
        return p;
    }
    
    @Override
    public List<Product> getProductsById(List<Long> ids) {
        return productRepo.findAllById(ids);
    }

    @Override
    public Product registerProduct(Product p) {
        return productRepo.save(p);
    }

    @Override
    public Product editProduct(Product newP) throws ProductNotFoundException {
        Product oldProduct = getProductById(newP.getId());
        if (oldProduct == null) {
            throw new ProductNotFoundException("Id informed is not in the system.");
        } else if (oldProduct.equals(newP)) {
            return oldProduct;
        } else {
            oldProduct.setName(newP.getName());
            oldProduct.setPrice(newP.getPrice());

            productRepo.save(oldProduct);
        }
        return oldProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }
}
