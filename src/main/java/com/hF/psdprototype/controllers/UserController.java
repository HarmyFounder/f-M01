package com.hF.psdprototype.controllers;

import com.hF.psdprototype.models.User;
import com.hF.psdprototype.services.MailSendService;
import com.hF.psdprototype.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSendService mailSendService;

    @GetMapping("/")
    public String getRedirect(){
        return "redirect:/products/all";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user) throws MessagingException {
        userService.createUser(user);
        mailSendService.send(user.getEmail(), "You are successfully signed up, " + user.getName(), "REGISTRATION");
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "profile";
    }
}
