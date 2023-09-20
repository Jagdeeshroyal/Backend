package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"playerId"})
)
public class Player {

    @Id
    private String playerId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private Team team;
    @ManyToMany(mappedBy = "players")
    private List<Match> matches;
    private String name;


}
