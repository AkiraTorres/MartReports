package com.poo.MartReports.Controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poo.MartReports.Exceptions.SaleNotFoundException;
import com.poo.MartReports.Models.Product;
import com.poo.MartReports.Models.Sale;
import com.poo.MartReports.Services.ProductServiceInterface;
import com.poo.MartReports.Services.SaleServiceInterface;
import com.poo.MartReports.Services.StoreServiceInterface;

@RestController
@RequestMapping("/sales")
public class SaleController implements SaleControllerInterface {
    @Autowired
    private SaleServiceInterface saleService;

    @Autowired
    private ProductServiceInterface produtoService;

    @Autowired
    private StoreServiceInterface storeService;

    @Override
    @GetMapping
    public ResponseEntity<String> findAllSales() {
        return new ResponseEntity<String>((saleService.findAllSales()).toString(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<String> getSaleById(@PathVariable Long id) {
        try {
            return new ResponseEntity<String>((saleService.getSaleById(id)).toString(), HttpStatus.OK);
        } catch (SaleNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<String> registerSale(@RequestBody Sale s, @RequestParam List<Long> produtoIds, @RequestParam Long storeId) {
        List<Product> products = produtoService.getProductsById(produtoIds);

        if (products.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        try {
            s.setStore(storeService.getStoreById(storeId));
            s.setProducts(products);
            s.setTotal(0.0);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        Sale newSale = saleService.registerSale(s);
        if (newSale != null) {
            return new ResponseEntity<String>(newSale.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("It was not possible to create the Sale", HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<String> editSale(@RequestBody Sale newS) {
        return null;
        // try {
        //     saleService.editSale(newS);
        //     return new ResponseEntity<String>(newS.toString(), HttpStatus.CREATED);
        // } catch (SaleNotFoundException e) {
        //     return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        // }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteSaleById(@PathVariable Long id) {
        saleService.deleteById(id);
        return new ResponseEntity<String>("Sale deleted successfully", HttpStatus.OK);
    }   
}
