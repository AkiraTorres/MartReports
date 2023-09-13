package com.poo.MartReports.Controllers;

import org.springframework.http.ResponseEntity;

import com.poo.MartReports.Models.Store;

public interface StoreControllerInterface {
    public ResponseEntity<String> findAllStores();

    public ResponseEntity<String> getStoreById(Long id);

    public ResponseEntity<String> registerStore(Store s);

    public ResponseEntity<String> editStore(Store newS);

    public ResponseEntity<String> deleteStoreById(Long id);
}
