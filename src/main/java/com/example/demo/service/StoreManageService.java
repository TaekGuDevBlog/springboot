package com.example.demo.service;

import com.example.demo.dao.StoreDao;
import com.example.demo.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreManageService {

    @Autowired
    StoreDao storeDao;

    public void StoreAdd(StoreVO storeVO) { storeDao.insertStoreInfo(storeVO); }

    public void StoreInfoUpdate(StoreVO storeVO) {
        storeDao.updateStoreInfo(storeVO);
    }

    public void StoreDelete(StoreVO storeVO) { storeDao.deleteStoreInfo(storeVO); }

    public List<StoreVO> StoreSelect(String name) { return storeDao.selectStoreInfo(name); }
}
