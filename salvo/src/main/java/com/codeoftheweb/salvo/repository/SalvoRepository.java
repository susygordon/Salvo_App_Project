package com.codeoftheweb.salvo.repository;

import com.codeoftheweb.salvo.model.Salvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalvoRepository extends JpaRepository<Salvo, Long> {
}
