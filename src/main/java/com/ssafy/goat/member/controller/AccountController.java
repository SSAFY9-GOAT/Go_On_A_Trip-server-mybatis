package com.ssafy.goat.member.controller;

import com.ssafy.goat.member.dto.LoginMember;
import com.ssafy.goat.member.dto.MemberAddDto;
import com.ssafy.goat.member.service.AccountService;
import com.ssafy.goat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;
    private final AccountService accountService;

    @GetMapping("/register")
    public String regist(){
        return "member/addMember";
    }

    @PostMapping("/register")
    public String regist(MemberAddDto memberAddDto){
        memberAddDto.setGender(memberAddDto.getGender().substring(0,1));
        memberService.signUp(memberAddDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, @RequestParam("userId") String userId, @RequestParam("userPassword") String userPwd){
        LoginMember loginMember = accountService.login(userId, userPwd);
        if(loginMember!=null){
            session.setAttribute("userinfo", loginMember);
            return "redirect:/";
        }else{
            model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
            return "account/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
