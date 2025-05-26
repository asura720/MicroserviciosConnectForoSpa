package connectforospa_gestiondecategorias.repository;

import connectforospa_gestiondecategorias.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionDeCategoriasRepository extends JpaRepository<Categorias, Long> {
}
