package com.projectcric.cricdata.controller;

import com.projectcric.cricdata.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {


    @Autowired
    private TeamService teamService;


    @PostMapping("/upload")
    public String uploadPlayers() {
        teamService.createTeam();
        return "Done";
    }

}
