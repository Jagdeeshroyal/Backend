package com.projectcric.cricdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seriesId;

    private String season;

    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "series")
    private List<Match> matches;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", referencedColumnName = "teamName")
    private Team winner;
}
