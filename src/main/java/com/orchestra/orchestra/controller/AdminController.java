package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Admin;
import com.orchestra.orchestra.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/signup", consumes = "application/json")
    private boolean adminSignup(@RequestBody Admin admin) {
        return adminService.signup(admin);
    }

    @GetMapping(path = "/login")
    private boolean login(Principal principal) {
        System.out.println(principal.getName() + " admin logged in");
        return true;
    }
}
