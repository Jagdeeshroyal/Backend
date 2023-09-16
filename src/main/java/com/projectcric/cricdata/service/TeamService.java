package com.projectcric.cricdata.service;

import com.projectcric.cricdata.model.Team;
import com.projectcric.cricdata.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    public static final List<String> ICC_TEAMS = new ArrayList<>(Arrays.asList(("Australia, Bangladesh, England, India, New Zealand, " +
            "Pakistan, South Africa, Sri Lanka, West Indies, Zimbabwe, " +
            "Afghanistan, " +
            "Argentina, Belgium, Bermuda, Botswana, Canada, Cayman Islands, Denmark, Fiji, France, Germany, Gibraltar, " +
            "Guernsey, Hong Kong, Ireland, Israel, Italy, Japan, Jersey, Kenya, Kuwait, Malaysia, Namibia, Nepal, Netherlands, " +
            "Nigeria, Oman, Papua New Guinea, Saudi Arabia, Scotland, Singapore, Suriname, Tanzania, Thailand, Uganda, " +
            "United Arab Emirates, United States of America, Vanuatu, Zambia").split(", ")));

    @Autowired
    private TeamRepository teamRepo;

    public void createTeam() {
        ICC_TEAMS.forEach(x -> {
            Team team = new Team();
            team.setTeamName(x);
            team = teamRepo.save(team);
        });
    }

    public Team getTeam(String teamName) {
        return teamRepo.findByTeamName(teamName).get();
    }

/*
    public Team getTeam(String name) {
        Optional<Team> optTeam = teamRepo.findByName(name);
        if (optTeam.isPresent()) {
            return optTeam.get();
        } else {
            Team team = new Team();
            team.setTeamName(name);
            team = teamRepo.save(team);
            return team;
        }
    }*/
}
