package com.example.Notificaciones2.model;

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
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacion;

    @JoinColumn(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private String estado;

    @Column()
    private LocalDateTime fechaEnvio;

}
