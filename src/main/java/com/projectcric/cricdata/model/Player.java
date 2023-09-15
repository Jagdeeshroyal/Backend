package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id",referencedColumnName = "teamId")
    private Team team;
    @ManyToMany(mappedBy = "players")
    private List<Match> matches;
    private String name;


}
