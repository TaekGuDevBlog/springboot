package com.example.demo.controller;


import com.example.demo.service.MenuManageService;
import com.example.demo.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
public class MenuController {
    //TODO : ~ 2019-03-09 Menu CRUD

    @Autowired
    MenuManageService menuManageService;

    @Autowired
    MenuVO menuVO;

    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    private void menuAdd(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "menu") String menu,
            @RequestParam(value = "price") int price) {
        menuVO.setStoreName(name);
        menuVO.setMenuName(menu);
        menuVO.setMenuPrice(price);
        menuManageService.MenuAdd(menuVO);
    }

    @RequestMapping(value = "/menu/updqte", method = RequestMethod.POST)
    private void menuUpdate(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "menu") String menu,
            @RequestParam(value = "price") int price) {
        menuVO.setStoreName(name);
        menuVO.setMenuName(menu);
        menuVO.setMenuPrice(price);
        menuManageService.MenuUpdate(menuVO);
    }

    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
    private void menuDelete(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "menu") String menu,
            @RequestParam(value = "price") int price) {
        menuVO.setStoreName(name);
        menuVO.setMenuName(menu);
        menuVO.setMenuPrice(price);
        menuManageService.MenuDelete(menuVO);
    }

    @RequestMapping(value = "/menu/select", method = RequestMethod.GET)
    private List<MenuVO> menuSelect(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "menu") String menu) {
        return menuManageService.MenuSelet(name, menu);
    }
}
