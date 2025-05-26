package com.example.AnuncioConnectForoSpa.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AnuncioConnectForoSpa.model.Anuncio;
import com.example.AnuncioConnectForoSpa.service.AnuncioService;


@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {
    private final AnuncioService service;

    public AnuncioController(AnuncioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Anuncio> getAll(){
        return service.getAll();

    }

    @GetMapping("/{id}")
    public Anuncio getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Anuncio create(@RequestBody Anuncio anuncio){
        return service.save(anuncio);
    }

    @PutMapping("/{id}")
    public Anuncio update(@PathVariable Long id, @RequestBody Anuncio anuncio){
        anuncio.setId_anuncio(id);
        return service.save(anuncio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }


}
