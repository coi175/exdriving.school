package com.exdriving.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Контроллер для входа в аккаунт
 */
@Controller
public class LoginController {
    // привязываем страницу логина к адресу /login
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    // привязываем страницу index к руту
    @GetMapping("/")
    public String index(Model model) {
        return "/index";
    }

    // адрес /default используем для перенапрявление на нужную страницу в зависимости от роли пользователя
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        // если админ, перенапралвяем на панель админа
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        // инструктор - панель инструктора
        else if(request.isUserInRole("ROLE_INSTRUCTOR")) {
            return "redirect:/instructor";
        }
        // клиент - панель клиента
        else if(request.isUserInRole("ROLE_CLIENT")) {
            return "redirect:/client";
        }
        // иначе на начальную страницу, которая не защищена spring security
        else return "redirect:/";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
