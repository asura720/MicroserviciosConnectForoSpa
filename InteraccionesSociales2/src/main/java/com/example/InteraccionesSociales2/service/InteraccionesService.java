package com.example.InteraccionesSociales2.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InteraccionesSociales2.model.Interacciones;
import com.example.InteraccionesSociales2.repository.InteraccionesRepository;
import com.example.InteraccionesSociales2.webClient.PublicacionesClient;
import com.example.InteraccionesSociales2.webClient.UsuariosClient;
import com.example.InteraccionesSociales2.webClient.dto.Publicacion;
import com.example.InteraccionesSociales2.webClient.dto.Usuario;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class InteraccionesService {



    private final InteraccionesRepository interaccionesRepository;
    private final PublicacionesClient publicacionesClient;
    private final UsuariosClient usuariosClient;

    @Autowired
    public InteraccionesService(InteraccionesRepository interaccionesRepository, 
                                PublicacionesClient publicacionesClient, 
                                UsuariosClient usuariosClient) {
        this.interaccionesRepository = interaccionesRepository;
        this.publicacionesClient = publicacionesClient;
        this.usuariosClient = usuariosClient;
    }

    public List<Interacciones> getAll() {
        return interaccionesRepository.findAll();
    }

    public Interacciones save(Interacciones interacciones) {
        Long usuario = interacciones.getIdUsuario();
        Long publicacion = interacciones.getIdpublicacion();

        Mono<Publicacion> resultadopubli = publicacionesClient.obtenerPublicacionPorId(publicacion);
        Mono<Usuario> resultado = usuariosClient.obtenerUsuarioPorId(usuario);
        Usuario usuarioEncontrado = resultado.block(); // Bloqueo porque es JPA (Sincrónico)
        Publicacion publicacionEncontrada = resultadopubli.block();


        if (usuarioEncontrado == null|| publicacionEncontrada == null) {
            throw new RuntimeException("Usuario no encontrado");

        }

        return interaccionesRepository.save(interacciones);
    }

    public void deletePorId(Long idInteraccion) {
        interaccionesRepository.deleteById(idInteraccion);
    }

    public List<Interacciones> obtenerInteraccionPorId(Long id) {
        return interaccionesRepository.findByIdUsuario(id);
    }

    public List<Interacciones> obtenerInteraccionesPorUsuario(Long idInteraccion) {
        return interaccionesRepository.findByIdInteraccion(idInteraccion);
    }

    



}
