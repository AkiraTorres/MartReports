package com.poo.MartReports.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.MartReports.Exceptions.StoreNotFoundException;
import com.poo.MartReports.Models.Store;
import com.poo.MartReports.Repositories.StoreRepository;

@Service
public class StoreService implements StoreServiceInterface {
    @Autowired
    private StoreRepository storeRepo;

    @Override
    public List<Store> findAllStores() {
        return storeRepo.findAll();
    }

    @Override
    public Store getStoreById(Long id) throws StoreNotFoundException {
        Store store = storeRepo.findById(id).get();
        if (store == null) {
            throw new StoreNotFoundException("Id informed is not in the system.");
        }
        return store;
    }

    @Override
    public Store registerStore(Store s) {
        storeRepo.save(s);
        return s;
    }

    @Override
    public Store editStore(Store newS) throws StoreNotFoundException {
        Store oldStore = getStoreById(newS.getId());
        if (oldStore == null) {
            throw new StoreNotFoundException("Id informed is not in the system.");
        } else if (oldStore.equals(newS)) {
            return oldStore; // if the edited user is equal to the old user, no changes are necessary
        } else {
            oldStore.setName(newS.getName());
            oldStore.setLocale(newS.getLocale());
            
            storeRepo.save(oldStore);
        }
        return oldStore;
    }

    @Override
    public void deleteById(Long id) {
        storeRepo.deleteById(id);
    }
}
