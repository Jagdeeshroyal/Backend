package com.projectcric.cricdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Venue venue;
    private Scorecard scorecard;
    private Series series;
    private LocalDate date;
    private List<Player> players;

}
