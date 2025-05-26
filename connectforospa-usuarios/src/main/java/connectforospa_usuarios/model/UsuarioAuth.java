package connectforospa_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAuth {

    @Id
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String estado;
}
