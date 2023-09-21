package com.projectcric.cricdata.repository;

import com.projectcric.cricdata.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {

    Optional<Series> findByNameAndSeason(String seriesName, String season);
}
