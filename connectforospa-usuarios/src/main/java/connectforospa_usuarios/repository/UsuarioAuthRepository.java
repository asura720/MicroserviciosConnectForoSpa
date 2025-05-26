package connectforospa_usuarios.repository;

import connectforospa_usuarios.model.UsuarioAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long> {
}
