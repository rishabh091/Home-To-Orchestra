package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.User;
import com.orchestra.orchestra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/signup", consumes = "application/json")
    public boolean signUp(@RequestBody User user) {
        return userService.signup(user);
    }

    @GetMapping(path = "/login")
    public boolean login(Principal principal) {
        System.out.println(principal.getName() + " logged in");
        return true;
    }

    @GetMapping(path = "/logout", produces = "application/json")
    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            request.getSession().invalidate();
        }

        System.out.println("Logout successful");
        return true;
    }
}
