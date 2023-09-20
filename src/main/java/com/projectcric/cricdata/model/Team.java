package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "team_tb",
        uniqueConstraints = @UniqueConstraint(columnNames = {"teamName"})
)
public class Team {

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @ManyToMany()
    private List<Match> matches;

    @Id
    private String teamName;

}
