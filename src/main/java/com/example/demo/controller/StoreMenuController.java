package com.example.demo.controller;


import com.example.demo.service.StoreMenuSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Component
public class StoreMenuController {

    @Autowired
    StoreMenuSelectService storeMenuSelectService;

    @RequestMapping(value = "/store/menu/search", method = RequestMethod.GET)
    private List<Map<String, Integer>> searchMenuInStore(
            @RequestParam(value = "storename") String storeName) {
        return storeMenuSelectService.selectMenuInStore(storeName);
    }
}
