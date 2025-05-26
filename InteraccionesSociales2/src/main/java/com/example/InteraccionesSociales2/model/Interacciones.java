package com.example.InteraccionesSociales2.model;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Interacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInteraccion;

    @Column(nullable = false)
    private String tipo;

    @Column()
    private LocalDateTime fechaPublicacion;


    @JoinColumn(name = "id_usuario") // O el nombre correcto de la columna en la BD
    private Long idUsuario;

    @JoinColumn(name = "id_publicacion")
    private Long idpublicacion;
}
