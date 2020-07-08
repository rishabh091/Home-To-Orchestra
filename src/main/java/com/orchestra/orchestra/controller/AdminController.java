package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Admin;
import com.orchestra.orchestra.services.AdminService;
import com.orchestra.orchestra.services.SingerService;
import com.orchestra.orchestra.services.VocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private VocalService vocalService;

    @PostMapping(path = "/signup", consumes = "application/json")
    public boolean adminSignup(@RequestBody Admin admin) {
        return adminService.signup(admin);
    }

    @GetMapping(path = "/login")
    public boolean login(Principal principal) {
        System.out.println(principal.getName() + " admin logged in");
        return true;
    }

    @GetMapping(path = "/logout")
    public String logout() {
        return "\"logout successful\"";
    }


    @PostMapping(path = "/edit", consumes = "application/json")
    public Admin editProfile(Principal principal, @RequestBody Admin admin) {
        return adminService.update(admin, principal);
    }
}
