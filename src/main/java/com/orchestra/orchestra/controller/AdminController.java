package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Admin;
import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.modals.SingingOption;
import com.orchestra.orchestra.services.AdminService;
import com.orchestra.orchestra.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SingerService singerService;

    @PostMapping(path = "/signup", consumes = "application/json")
    private boolean adminSignup(@RequestBody Admin admin) {
        return adminService.signup(admin);
    }

    @GetMapping(path = "/login")
    private boolean login(Principal principal) {
        System.out.println(principal.getName() + " admin logged in");
        return true;
    }

    @PostMapping(path = "/addSinger", consumes = "application/json")
    private String addSinger(Principal principal, @RequestBody String singer) {
        return singerService.addSinger(singer, principal);
    }

    @GetMapping(path = "/singers", produces = "application/json")
    private String getSingers(Principal principal) {
        return singerService.getSingers(principal);
    }

    @GetMapping(path = "/singer/{id}", produces = "application/json")
    private List<SingingOption> getSinger(Principal principal, @PathVariable long id) {
        return singerService.getSinger(id, principal);
    }
}
