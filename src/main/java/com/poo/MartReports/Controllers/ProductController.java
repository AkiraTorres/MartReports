package com.poo.MartReports.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poo.MartReports.Exceptions.ProductNotFoundException;
import com.poo.MartReports.Models.Product;
import com.poo.MartReports.Services.ProductServiceInterface;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductControllerInterface {
    @Autowired
    private ProductServiceInterface productService;

    @Override
    @GetMapping
    public ResponseEntity<String> findAllProducts() {
        return new ResponseEntity<String>((productService.findAllProducts()).toString(),HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findProductById(@PathVariable Long id) {
        try {
            return new ResponseEntity<String>((productService.getProductById(id)).toString(), HttpStatus.CREATED);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<String> registerProduct(@RequestBody Product p) {
        Product newProduct = productService.registerProduct(p);
        if (newProduct != null) {
            return new ResponseEntity<String>(newProduct.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("It was not possible to register the user", HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<String> editProduct(@RequestBody Product newP) {
        try {
            productService.editProduct(newP);
            return new ResponseEntity<String>(newP.toString(), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
}
