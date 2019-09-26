package com.codeoftheweb.salvo.service.impl;

import com.codeoftheweb.salvo.model.Salvo;
import com.codeoftheweb.salvo.repository.SalvoRepository;
import com.codeoftheweb.salvo.service.SalvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalvoServiceImpl implements SalvoService {

    @Autowired
    private SalvoRepository salvoRepository;


    @Override
    public Optional<Salvo> findById(Long id) {
        return salvoRepository.findById(id);

    }

    @Override
    public List<Salvo> findAll() {
        return salvoRepository.findAll();
    }
}
