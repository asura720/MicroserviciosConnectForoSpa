package com.example.SoporteTecnico.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SoporteTecnico.Repository.SoporteTecnicoRepository;
import com.example.SoporteTecnico.model.SoporteTecnico;
import com.example.SoporteTecnico.webClient.UsuariosClient;
import com.example.SoporteTecnico.webClient.dto.Usuario;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SoporteTecnicoService {
    
    private final SoporteTecnicoRepository soporteTecnicoRepository;
    private final UsuariosClient usuariosClient;

    public SoporteTecnicoService(SoporteTecnicoRepository soporteTecnicoRepository, 
                                UsuariosClient usuariosClient) {
        this.soporteTecnicoRepository = soporteTecnicoRepository;
        this.usuariosClient = usuariosClient;
    }


    public List<SoporteTecnico> getAll(){
        return soporteTecnicoRepository.findAll();
    }


    public SoporteTecnico save(SoporteTecnico soporteTecnico){
        Long usuario = soporteTecnico.getIdUsuario();

        Mono<Usuario> resultado = usuariosClient.obtenerUsuarioPorId(usuario);
        Usuario usuarioEncontrado = resultado.block(); 


        if(usuarioEncontrado == null){
            throw new RuntimeException("Usuario no encontrado");
        } 
        return soporteTecnicoRepository.save(soporteTecnico);
    }

    public void delete(Long idSoporteTecnico){
        soporteTecnicoRepository.deleteById(idSoporteTecnico);
    }

    public List<SoporteTecnico> obtenerSoportePorUsuario(Long id) {
        return soporteTecnicoRepository.findByIdUsuario(id);
    }

    public SoporteTecnico obtenerSoportePorId(Long id) {
        return soporteTecnicoRepository.findById(id).orElse(null);
    }



}
