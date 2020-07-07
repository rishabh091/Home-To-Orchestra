package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.User;
import com.orchestra.orchestra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/logout")
    public String logout() {
        return "\"Logout Successful\"";
    }

    @PostMapping(path = "/editProfile", consumes = "application/json")
    public User editProfile(Principal principal, @RequestBody User user) {
        return userService.update(user, principal);
    }
}
