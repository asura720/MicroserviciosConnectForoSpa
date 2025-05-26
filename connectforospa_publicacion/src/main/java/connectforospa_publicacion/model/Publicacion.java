package connectforospa_publicacion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    private String titulo;
    private String contenido;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion = LocalDateTime.now();

    @Column(nullable = false)
    private Long idCategoria; 
}