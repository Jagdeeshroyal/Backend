package com.projectcric.cricdata.controller;

import com.projectcric.cricdata.model.*;
import com.projectcric.cricdata.repository.PlayerRepository;
import com.projectcric.cricdata.service.PlayerService;
import com.projectcric.cricdata.service.TeamService;
import com.projectcric.cricdata.util.CreateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController {


    @Autowired
    private PlayerService playerService;

    @Autowired
    private CreateUtility createUtil;


    @PostMapping("/upload")
    public String uploadPlayes() throws IOException {
        playerService.uploadPlayers();
        return "Hi Jaggu";
    }

}