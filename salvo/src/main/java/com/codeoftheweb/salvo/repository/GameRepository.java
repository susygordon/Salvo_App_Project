package com.codeoftheweb.salvo.repository;

import java.util.Date;
import java.util.List;

import com.codeoftheweb.salvo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByCreationDate(Date creationDate);
}


