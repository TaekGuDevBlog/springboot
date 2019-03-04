package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManageService {

    @Autowired
    UserDao userDao;

    public void UserAdd(MemberVO memberVO) {
        userDao.insertUserInfo(memberVO);
    }

    //change password
    public void UserInfoUpdate(MemberVO memberVO) {
        userDao.updateUserInfo(memberVO);
    }

    //if password equals delete
    public void UserDelete(MemberVO memberVO) { userDao.deleteUserInfo(memberVO); }

    public MemberVO UserSelectOne(String memberId) {
        return userDao.selectOneUserInfo(memberId);
    }

    public List<MemberVO> UserSelectAll() { return userDao.selectAllUserInfo(); }
}
