package com.codeoftheweb.salvo.repository;

import com.codeoftheweb.salvo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long> {
	List<Player> findByEmail(String email);
	Optional<Player> findFirstByEmail(String email);
}