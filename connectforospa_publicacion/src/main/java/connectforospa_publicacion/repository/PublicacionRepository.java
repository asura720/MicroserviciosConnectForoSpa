package connectforospa_publicacion.repository;

import connectforospa_publicacion.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByIdUsuario(Long idUsuario);
    List<Publicacion> findByIdCategoria(Long idCategoria);
}
