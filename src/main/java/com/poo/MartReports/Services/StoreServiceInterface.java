package com.poo.MartReports.Services;

import java.util.List;

import com.poo.MartReports.Exceptions.StoreNotFoundException;
import com.poo.MartReports.Models.Store;

public interface StoreServiceInterface {
     public List<Store> findAllStores();

    public Store getStoreById(Long id) throws StoreNotFoundException;

    public Store registerStore(Store s);

    public Store editStore(Store newS) throws StoreNotFoundException;

    public void deleteById(Long id);

}