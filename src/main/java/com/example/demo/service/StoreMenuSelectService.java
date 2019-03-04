package com.example.demo.service;

import com.example.demo.dao.StoreMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class StoreMenuSelectService {

    @Autowired
    StoreMenuDao storeMenuDao;

    public List<Map<String, Integer>> selectMenuInStore(String storeName){
        return storeMenuDao.selectStoreMenu(storeName);
    }
}
