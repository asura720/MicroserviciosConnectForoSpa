package com.example.InteraccionesSociales2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InteraccionesSociales2.model.Interacciones;

@Repository
public interface InteraccionesRepository extends JpaRepository<Interacciones, Long>{
    List<Interacciones> findByIdUsuario(long idUsuario);
    List<Interacciones> finByIdPublicacion(long idPublicacion);
    List<Interacciones> findByIdInteraccion(long idInteraccion);
}
