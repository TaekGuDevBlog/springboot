package com.example.demo.service;

import com.example.demo.dao.MenuDao;
import com.example.demo.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuManageService {

    @Autowired
    MenuDao menuDao;

    public void MenuAdd(MenuVO menuVO) {
        menuDao.insertMenuInfo(menuVO);
    }

    public void MenuUpdate(MenuVO menuVO) {
        menuDao.updateMenuInfo(menuVO);
    }

    public void MenuDelete(MenuVO menuVO) {
        menuDao.deleteMenuInfo(menuVO);
    }

    public List<MenuVO> MenuSelet(String name, String menu) {
        return menuDao.selectMenuInfo(name, menu);
    }
}

