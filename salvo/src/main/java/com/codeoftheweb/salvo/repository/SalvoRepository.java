package com.codeoftheweb.salvo.repository;

import com.codeoftheweb.salvo.model.Salvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalvoRepository extends JpaRepository<Salvo, Long> {
    Optional<Salvo> findById(Long id);
}
