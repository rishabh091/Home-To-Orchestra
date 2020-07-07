package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Admin;
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

    @PostMapping(path = "/addSinger", consumes = "application/json")
    public String addSinger(Principal principal, @RequestBody String singer) {
        return singerService.addSinger(singer, principal);
    }

    @GetMapping(path = "/singers", produces = "application/json")
    public String getSingers(Principal principal) {
        return singerService.getSingers(principal);
    }

    @GetMapping(path = "/singer/{id}", produces = "application/json")
    public List<SingingOption> getSinger(Principal principal, @PathVariable long id) {
        return singerService.getSinger(id, principal);
    }

    @PostMapping(path = "/updateSinger", consumes = "application/json")
    public String updateSinger(Principal principal, @RequestBody String json) {
        return singerService.updateSinger(json, principal);
    }

    @GetMapping(path = "/deleteSinger/{id}")
    public String deleteSinger(Principal principal, @PathVariable long id) {
        return singerService.delete(id, principal);
    }
}
