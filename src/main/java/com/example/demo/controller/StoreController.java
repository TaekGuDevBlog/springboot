package com.example.demo.controller;

import com.example.demo.service.StoreManageService;
import com.example.demo.vo.StoreVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@Slf4j
public class StoreController {
    //TODO: 2019-03-03 음식점 CRUD 필요
    //TODO: 2019-03-03 Store Branch Name Column Add

    @Autowired
    StoreManageService storeManageService;

    @Autowired
    StoreVO storeVO;

    @RequestMapping(value = "/store/add", method = RequestMethod.POST)
    private void storeAdd(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "branch", defaultValue = "nobranch", required = false) String branch) {
            storeVO.setStoreName(name);
            storeVO.setStoreLocation(location);
            storeVO.setBranchName(branch);
            storeManageService.StoreAdd(storeVO);
    }

    @RequestMapping(value = "/store/updqte", method = RequestMethod.POST)
    private void storeUpdate(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "branch", defaultValue = "nobranch", required = false) String branch) {
            storeVO.setStoreName(name);
            storeVO.setStoreLocation(location);
            storeVO.setBranchName(branch);
            storeManageService.StoreInfoUpdate(storeVO);
    }

    @RequestMapping(value = "/store/delete", method = RequestMethod.POST)
    private void storeDelete(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "branch", defaultValue = "nobranch", required = false) String branch) {
            storeVO.setStoreName(name);
            storeVO.setBranchName(branch);
            storeManageService.StoreDelete(storeVO);
    }

    @RequestMapping(value = "/store/select", method = RequestMethod.GET)
    private List<StoreVO> storeSelect(
            @RequestParam(value = "name") String name) {
            return storeManageService.StoreSelect(name);
    }
}
