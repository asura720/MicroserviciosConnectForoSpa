package com.example.AnuncioConnectForoSpa.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_anuncio;

    private String titulo;
    private String contenido;
    private String fecha_inicio;
    private String fecha_fin;

}
