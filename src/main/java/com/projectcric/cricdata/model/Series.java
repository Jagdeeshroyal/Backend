package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seriesId;

    private String name;
    @OneToMany(mappedBy = "series")
    private List<Match> matches;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner",referencedColumnName = "teamName")
    private Team winner;
}
