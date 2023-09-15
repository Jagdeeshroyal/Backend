package com.projectcric.cricdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @PostMapping ("/")
    public String working() {

        return "Hi Jaggu";
    }

}
