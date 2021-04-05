package com.exdriving.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "/index";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/";
        }
        else if(request.isUserInRole("ROLE_INSTRUCTOR")) {
            return "redirect:/instructor/";
        }
        else if(request.isUserInRole("ROLE_CLIENT")) {
            return "redirect:/client/";
        }
        else return "redirect:/";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
