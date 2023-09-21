package com.projectcric.cricdata.service;

import com.projectcric.cricdata.model.Player;
import com.projectcric.cricdata.model.Team;
import com.projectcric.cricdata.repository.PlayerRepository;
import com.projectcric.cricdata.repository.TeamRepository;
import com.projectcric.cricdata.util.CreateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private CreateUtility createUtil;
    @Autowired
    private TeamService teamService;


    public void uploadPlayers() throws IOException {
        for (File file : createUtil.getFiles()) {
            Map<String, String> pair = new HashMap<>();//{playerName,TeamName}
            List<String> fileData = createUtil.getFileData(file.getPath());
            Map<String, Team> teams = new HashMap<>();
            for (String str : fileData) {
                String[] splitData = str.split(",");
                if (splitData[0].equalsIgnoreCase("info")) {
                    if (splitData[1].equalsIgnoreCase("team")) {
                        if (TeamService.ICC_TEAMS.contains(splitData[2])) {
                            teams.put(splitData[2], teamService.getTeam(splitData[2]));
                        } else {
                            break;
                        }
                    } else if (splitData[1].equalsIgnoreCase("player")) {
                        pair.put(splitData[3], splitData[2]);
                    } else if (splitData[1].equalsIgnoreCase("registry") &&
                            !playerPresent(splitData[4]) && pair.get(splitData[3]) != null) {
                        Player player = new Player();
                        player.setName(splitData[3]);
                        player.setTeam(teams.get(pair.get(splitData[3])));
                        player.setPlayerId(splitData[4]);
                        playerRepo.save(player);
                    }
                }
            }
        }
    }

    public boolean playerPresent(String registry) {
        boolean present = playerRepo.findById(registry).isPresent();
        return present;
    }

    public Player getPlayer(String playerName) {
        Player player = playerRepo.findByName(playerName);
        return player;
    }
}

