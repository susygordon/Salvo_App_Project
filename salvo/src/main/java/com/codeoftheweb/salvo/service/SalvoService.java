package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Salvo;

import java.util.List;
import java.util.Optional;

public interface SalvoService {
	Optional<Salvo> findById(Long id);

	List<Salvo> findAll();
}
