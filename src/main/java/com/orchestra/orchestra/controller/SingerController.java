package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping(path = "/singers", produces = "application/json")
    public List<Singer> getSingers(Principal principal) {
        return singerService.getSingers(principal);
    }

    @GetMapping(path = "/singer/{id}", produces = "application/json")
    public Singer getSinger(Principal principal, @PathVariable long id) {
        return singerService.getSinger(id, principal);
    }

    @PostMapping(path = "/addSinger")
    public Singer addSinger(Principal principal, @RequestBody Singer singer) {
        return singerService.addSinger(singer, principal);
    }

    @PostMapping(path = "/updateSinger", consumes = "application/json")
    public String updateSinger(Principal principal, @RequestBody Singer singer) {
        return singerService.updateSinger(singer, principal);
    }

    @GetMapping(path = "/deleteSinger/{id}")
    public String deleteSinger(Principal principal, @PathVariable long id) {
        return singerService.delete(id, principal);
    }

    @PostMapping(path = "/singer/{id}/upload")
    public String uploadPic(Principal principal, @RequestBody String data, @PathVariable long id) {
        return singerService.uploadImage(data, id, principal);
    }
}
