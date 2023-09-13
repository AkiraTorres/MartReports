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

import com.poo.MartReports.Exceptions.StoreNotFoundException;
import com.poo.MartReports.Models.Store;
import com.poo.MartReports.Services.StoreServiceInterface;

@RestController
@RequestMapping("/stores")
public class StoreController implements StoreControllerInterface {
    @Autowired
    private StoreServiceInterface storeService;

    @Override
    @GetMapping
    public ResponseEntity<String> findAllStores() {
        return new ResponseEntity<String>((storeService.findAllStores()).toString(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<String> getStoreById(@PathVariable Long id) {
        try {
            return new ResponseEntity<String>((storeService.getStoreById(id)).toString(), HttpStatus.OK);
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<String> registerStore(@RequestBody Store s) {
        Store newStore = storeService.registerStore(s);
        if (newStore != null) {
            return new ResponseEntity<String>(newStore.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("It was not possible to create the Store", HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<String> editStore(@RequestBody Store newS) {
        try {
            storeService.editStore(newS);
            return new ResponseEntity<String>(newS.toString(), HttpStatus.CREATED);
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteStoreById(@PathVariable Long id) {
        storeService.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
