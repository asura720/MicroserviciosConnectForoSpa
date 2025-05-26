package com.example.ReportesyModeracionConnectForoSpa.service;

import org.springframework.stereotype.Service;

import com.example.ReportesyModeracionConnectForoSpa.model.Reporte;
import com.example.ReportesyModeracionConnectForoSpa.repository.ReporteRepository;
import com.example.ReportesyModeracionConnectForoSpa.webclient.UsuarioClient;

import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ReporteService {

    private final ReporteRepository repository;
    private final UsuarioClient usuarioClient;

    public ReporteService(ReporteRepository repository, UsuarioClient usuarioClient){
        this.repository = repository;
        this.usuarioClient = usuarioClient;
    }

    public List<Reporte> getAll(){
        return repository.findAll();
    }

    public Reporte getById(Long   id){
        return repository.findById(id).orElse(null);
    }

    public Reporte save(Reporte reporte){
        //verificar si el usuario existe en el microservicio externo
        Mono<Boolean> usuarioExiste = usuarioClient.obtenerUsuarioPorId(reporte.getId_usuario())
                .map(user -> true)
                .defaultIfEmpty(false);


        if (usuarioExiste.block()) {
            return repository.save(reporte);
        } else {
            throw new RuntimeException("El usuario no existe en el microservicio de usuarios.");
        }
    }
    
    public void delete(Long id){
        repository.deleteById(id);
    }

    }


