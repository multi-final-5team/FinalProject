package com.multi.happytails.member.controller;

import com.multi.happytails.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/main/main";
    }
}
