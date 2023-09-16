package com.projectcric.cricdata.repository;

import com.projectcric.cricdata.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    Optional<Player> findByNameAndTeamTeamName(String name, String teamName);
}
