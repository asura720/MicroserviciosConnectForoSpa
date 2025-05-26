package com.example.Comentarios2.model;

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
@Table(name = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentario;

    @Column(nullable = false)
    private String contenido;

    @Column()
    private LocalDateTime fechaPublicacion;

    @JoinColumn(name = "id_usuario") // O el nombre correcto de la columna en la BD
    private Long idUsuario;
    
    @JoinColumn(name = "id_publicacion")
    private Long idPublicacion;
}
