package connectforospa_publicacion.webclient;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String estado; 
}
