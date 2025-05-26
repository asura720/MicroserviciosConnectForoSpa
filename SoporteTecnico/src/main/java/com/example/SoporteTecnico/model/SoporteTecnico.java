package com.example.SoporteTecnico.model;

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
@Table(name = "soporteTecnico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoporte;

    @JoinColumn(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private String descripcion;

    @Column()
    private LocalDateTime fechaSoporte;

    @Column(nullable = false)
    private String estado;
}
