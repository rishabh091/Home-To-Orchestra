package com.orchestra.orchestra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //this is a test controller and serve no real purpose
    @GetMapping(path = "/test", produces = "application/json")
    public String test() {
        return "\"Test successful\"";
    }
}
