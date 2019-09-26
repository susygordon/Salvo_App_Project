package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.ShipRepository;
import com.codeoftheweb.salvo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipRepository shipRepository;

    @Override
    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);

    }


    @Override
    public List<Ship> findAll() {
        return shipRepository.findAll();
    }
}
