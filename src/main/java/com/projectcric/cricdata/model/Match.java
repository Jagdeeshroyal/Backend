package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "match_tb")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "match_players_tb",
            joinColumns = @JoinColumn(name = "match_id", referencedColumnName = "matchId"),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "playerId")
    )
    private List<Player> players;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "venue_name", referencedColumnName = "venueName")
    private Venue venue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "series_id",
            referencedColumnName = "seriesId"
    )
    private Series series;
    @OneToMany(mappedBy = "match")
    private List<Score> scores;

    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "match_team_tb",
            inverseJoinColumns = @JoinColumn(name = "team_name", referencedColumnName = "teamName"),
            joinColumns = @JoinColumn(name = "match_id", referencedColumnName = "matchId")
    )
    private List<Team> teams;
    private String winner;
    private String playerOfTheMatch;

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}





