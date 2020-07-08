package com.orchestra.orchestra.controller;

import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.modals.Vocal_Singer;
import com.orchestra.orchestra.services.VocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/vocal")
public class VocalController {

    @Autowired
    private VocalService vocalService;

    @PostMapping(path = "/add", consumes = "application/json")
    public Vocal addVocal(Principal principal, @RequestBody Vocal vocal) {
        return vocalService.addVocal(vocal, principal);
    }

    @PostMapping(path = "/upload/{id}/cover")
    public Vocal uploadCover(Principal principal, @RequestBody String image, @PathVariable long id) {
        return vocalService.uploadCover(image, id, principal);
    }

    @GetMapping(path = "/getAll", produces = "application/json")
    public List<Vocal> getAll() {
        return vocalService.getAll();
    }

    @GetMapping(path = "/get/{id}", produces = "application/json")
    public List<Vocal_Singer> getById(@PathVariable long id) {
        return vocalService.getById(id);
    }

    @GetMapping(path = "/{vocalId}/singer/{singerId}", produces = "application/json")
    public Vocal_Singer addSingerToVocal(Principal principal, @PathVariable long vocalId,
                                               @PathVariable long singerId) {
        return vocalService.addSingerToVocal(vocalId, singerId, principal);
    }
}
