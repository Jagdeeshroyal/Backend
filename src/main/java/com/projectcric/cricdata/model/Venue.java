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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"venueName"}))
public class Venue {


    @Id
    private String venueName;

    private String city;

    @OneToMany(mappedBy = "venue")
    private List<Match> matches;

}
