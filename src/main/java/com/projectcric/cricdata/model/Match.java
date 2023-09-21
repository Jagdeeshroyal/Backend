package com.projectcric.cricdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "match_tb")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "match_players_tb",
            joinColumns = @JoinColumn(name = "match_id", referencedColumnName = "matchId"),
            inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "playerId")
    )
    private List<Player> players;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "venue_name", referencedColumnName = "venueName")
    private Venue venue;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "series_id",
            referencedColumnName = "seriesId"
    )
    private Series series;
    @JsonIgnore
    @OneToMany(mappedBy = "match")
    private List<Score> scores;

    private LocalDate date;

    @JsonIgnore
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
    private int matchNumber;
    private String tossWinner;
    private String tossDecision;
    private String wonBy;

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }


}





