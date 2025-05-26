package com.example.MetricasSistemaConnectForoSpa.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricaSistema {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_metrica;

    private String tipo_metrica;
    private String valor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_registro;
}
