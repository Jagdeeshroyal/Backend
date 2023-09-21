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
@Getter
@Setter
@ToString
@Table(name = "team_tb",
        uniqueConstraints = @UniqueConstraint(columnNames = {"teamName"})
)
public class Team {
@JsonIgnore
    @OneToMany(mappedBy = "team")
    private List<Player> players;
@JsonIgnore
    @ManyToMany()
    private List<Match> matches;

    @Id
    private String teamName;

}
