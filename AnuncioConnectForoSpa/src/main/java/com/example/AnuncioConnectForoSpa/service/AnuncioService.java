package com.example.AnuncioConnectForoSpa.service;


import org.springframework.stereotype.Service;

import com.example.AnuncioConnectForoSpa.model.Anuncio;
import com.example.AnuncioConnectForoSpa.repository.AnuncioRepository;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository repository;

    public AnuncioService(AnuncioRepository repository){
        this.repository = repository;
    }

    public List<Anuncio> getAll() {
        return repository.findAll();
    }

    public Anuncio getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Anuncio save(Anuncio anuncio) {
        return repository.save(anuncio);
    }

      public void delete(Long id) {
        repository.deleteById(id);
    }

}
