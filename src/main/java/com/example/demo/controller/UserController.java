package com.example.demo.controller;

import com.example.demo.service.UserManageService;
import com.example.demo.vo.MemberVO;
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
public class UserController {

    @Autowired
    UserManageService userManageService;

    @Autowired
    MemberVO memberVO;

    //member add
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    private void userAdd(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pw") String pw,
            @RequestParam(value = "name") String name) {
        memberVO.setMemberId(id);
        memberVO.setMemberPw(pw);
        memberVO.setMemberName(name);
        userManageService.UserAdd(memberVO);
    }

    //member info update
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public void userUpdate(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pw") String pw) {
        memberVO.setMemberId(id);
        memberVO.setMemberPw(pw);
        userManageService.UserInfoUpdate(memberVO);
    }

    //member delete ( if password equal )
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public void userDelete(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "pw") String pw) {
        memberVO.setMemberId(id);
        memberVO.setMemberPw(pw);
        userManageService.UserDelete(memberVO);
    }

    //member select ( one person )
    @RequestMapping(value = "/user/selectone", method = RequestMethod.GET)
    public MemberVO userSelectOne(
            @RequestParam(value = "name") String name) {
        return userManageService.UserSelectOne(name);
    }

    //member select ( all )
    @RequestMapping(value = "/user/selectall", method = RequestMethod.GET)
    public List<MemberVO> userSelectAll() {
        return userManageService.UserSelectAll();
    }
}
