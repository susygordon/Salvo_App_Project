package com.codeoftheweb.salvo.service;

import com.codeoftheweb.salvo.model.Ship;

import java.util.List;
import java.util.Optional;

public interface ShipService {
	Optional<Ship> findById(Long id);

	List<Ship> findAll();
}
