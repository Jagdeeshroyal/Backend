package com.projectcric.cricdata.controller;

import com.projectcric.cricdata.model.Match;
import com.projectcric.cricdata.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/upload")
    public List<Match> uploadMatch() throws IOException {
        List<Match> matches = matchService.uploadMatches();
        return matches;
    }

}
