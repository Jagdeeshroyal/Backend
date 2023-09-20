package com.projectcric.cricdata.repository;

import com.projectcric.cricdata.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, String> {
    Optional<Venue> findByVenueName(String venueName);

    List<Venue> findByCityIsNull();
}
