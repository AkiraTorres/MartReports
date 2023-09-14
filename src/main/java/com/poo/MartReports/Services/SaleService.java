package com.poo.MartReports.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.MartReports.Exceptions.SaleNotFoundException;
import com.poo.MartReports.Models.Sale;
import com.poo.MartReports.Repositories.SaleRepository;

@Service
public class SaleService implements SaleServiceInterface {
    @Autowired
    private SaleRepository saleRepo;

    @Override
    public List<Sale> findAllSales() {
        return saleRepo.findAll();
    }

    @Override
    public Sale getSaleById(Long id) throws SaleNotFoundException {
        Sale s = saleRepo.findById(id).orElseThrow(() -> new SaleNotFoundException("Id informed is not in the system."));
        return s;
    }

    @Override
    public Sale registerSale(Sale s) {
        saleRepo.save(s);
        return s;
    }

    @Override
    public Sale editSale(Sale newS) throws SaleNotFoundException {
        Sale oldS = saleRepo.findById(newS.getId()).orElseThrow(() -> new SaleNotFoundException());
        if (oldS.equals(newS)) {
            return oldS;
        } else {
            oldS.setStore(newS.getStore());
            oldS.setDate(newS.getDate());
            oldS.setTotal(newS.getTotal());
            oldS.setProducts(newS.getProducts());
            
            saleRepo.save(oldS);
        }
        return oldS;
    }

    @Override
    public void deleteById(Long id) {
        saleRepo.deleteById(id);
    }
}
