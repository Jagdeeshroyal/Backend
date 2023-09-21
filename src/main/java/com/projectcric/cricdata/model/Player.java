package com.projectcric.cricdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"playerId"})
)
public class Player {

    @Id
    private String playerId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private Team team;
    @JsonIgnore
    @ManyToMany(mappedBy = "players")
    private List<Match> matches;
    private String name;


}
