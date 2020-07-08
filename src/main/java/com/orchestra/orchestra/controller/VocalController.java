package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.services.VocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class VocalController {

    @Autowired
    private VocalService vocalService;

    @PostMapping(path = "/vocal/add", consumes = "application/json")
    public Vocal addVocal(Principal principal, @RequestBody Vocal vocal) {
        return vocalService.addVocal(vocal, principal);
    }
}
