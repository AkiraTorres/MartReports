package com.poo.MartReports.Services;

import java.util.List;

import com.poo.MartReports.Exceptions.SaleNotFoundException;
import com.poo.MartReports.Models.Sale;

public interface SaleServiceInterface {
    
    public List<Sale> findAllSales();

    public Sale getSaleById(Long id) throws SaleNotFoundException;

    public Sale registerSale(Sale s);

    public Sale editSale(Sale newS) throws SaleNotFoundException;

    public void deleteById(Long id);

}
