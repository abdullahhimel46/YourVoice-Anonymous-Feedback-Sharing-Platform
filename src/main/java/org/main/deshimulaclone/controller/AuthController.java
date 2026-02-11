package org.main.deshimulaclone.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.main.deshimulaclone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam @Nullable String error, @RequestParam @Nullable String logout, RedirectAttributes redirectAttributes){
        if (error != null && error.equals("true")) // an error has occurred during login
        {
            redirectAttributes.addFlashAttribute("error","Invalid credentials!");
            return "redirect:/login";
        } else if (logout != null && logout.equals("true")) {
            redirectAttributes.addFlashAttribute("feedbackMessage","Logout successful.");
            return "redirect:/login";
        }
        // if no upper condition is true, then return the login page, no need to redirect
        return "login-page";
    }

    @GetMapping("/registration-page")
    public String registrationPageGet(){
        
        return "registration-page";
    }

    @PostMapping("/registration")
    public String doUserRegistration(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes){
        try {
            userService.registerUser(username,password);
            redirectAttributes.addFlashAttribute("message","Registration successful.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
        }
        return "redirect:/registration-page";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(HttpServletRequest httpServletRequest){
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")){
            return "redirect:/home-page";
        } else if (httpServletRequest.isUserInRole("ROLE_USER")) {
            return "redirect:/user/dashboard";
        }

        return "redirect:/home-page";
    }
}
