package com.projectcric.cricdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"venueName"}))
public class Venue {


    @Id
    private String venueName;

    private String city;


    @JsonIgnore
    @OneToMany(mappedBy = "venue")
    private List<Match> matches;

}
