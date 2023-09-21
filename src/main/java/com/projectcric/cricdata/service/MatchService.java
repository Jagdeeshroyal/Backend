package com.projectcric.cricdata.service;

import com.projectcric.cricdata.model.*;
import com.projectcric.cricdata.repository.MatchRepository;
import com.projectcric.cricdata.util.CreateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;
    @Autowired
    private CreateUtility createUtil;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private VenueService venueService;
    @Autowired
    private PlayerService playerService;


    public List<Match> uploadMatches() throws IOException {
        List<Match> matches = new ArrayList<>();
        File[] files = createUtil.getFiles();
        System.out.println(files.length);
//        for (int i = 0; i <= 4000; i++) {
//            File file = files[i];
//            Match match = uploadMatch(file);
//            if (match.getMatchId() > 1) {
//                matches.add(match);
//            }
//        }
        for (File file : files) {
            Match match = uploadMatch(file);
            if (match.getMatchId() > 1) {
                matches.add(match);
            }
        }
        return matches;
    }

    public Match uploadMatch(File file) throws IOException {
        List<String> listStr = Files.lines(Path.of(file.getPath())).toList();
        Match match = new Match();
        Series series = new Series();
        String seriesName = "";
        String season = "";
        Venue venue = new Venue();
        List<Team> teams = new ArrayList<>();
        Score score = new Score();
        boolean validMatch = true;
        for (String str : listStr) {
            String[] splitedStr = str.split(",");
            if (splitedStr[0].equalsIgnoreCase("info")) {
                if (splitedStr[1].equalsIgnoreCase("team")) {
                    if (TeamService.ICC_TEAMS.contains(splitedStr[2])) {
                        Team team = teamService.getTeam(splitedStr[2]);
                        teams.add(team);
                    } else {
                        validMatch = false;
                        break;
                    }
                } else if (splitedStr[1].equalsIgnoreCase("date")) {
                    match.setDate(LocalDate.parse(splitedStr[2], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                } else if (splitedStr[1].equalsIgnoreCase("event")) {
                    seriesName = splitedStr[2];
                } else if (splitedStr[1].equalsIgnoreCase("season")) {
                    season = splitedStr[2];
                } else if (splitedStr[1].equalsIgnoreCase("venue")) {
                    series = seriesService.getSeries(seriesName, season);
                    venue = venueService.getVenue(splitedStr[2]);
                    match.setVenue(venue);
                    match.setSeries(series);
                } else if (splitedStr[1].equalsIgnoreCase("toss_winner")) {
                    match.setTossWinner(splitedStr[2]);
                } else if (splitedStr[1].equalsIgnoreCase("toss_decision")) {
                    match.setTossDecision(splitedStr[2]);
                } else if (splitedStr[1].equalsIgnoreCase("winner") || splitedStr[1].equalsIgnoreCase("outcome")) {
                    match.setWinner(splitedStr[2]);
                } else if (splitedStr[1].equalsIgnoreCase("winner_runs")) {
                    match.setWonBy(splitedStr[2].toString() + "runs");
                } else if (splitedStr[1].equalsIgnoreCase("winner_wickets")) {
                    match.setWonBy(splitedStr[2].toString() + "wickets");
                } else if (splitedStr[1].equalsIgnoreCase("player_of_match")) {
                    match.setPlayerOfTheMatch(splitedStr[2]);
                }
            } else if (splitedStr[0].equalsIgnoreCase("ball")) {
                break;
            }
        }
        if (validMatch)
            match = matchRepo.save(match);
        else
            match.setMatchId(-1);
        return match;
    }
}
