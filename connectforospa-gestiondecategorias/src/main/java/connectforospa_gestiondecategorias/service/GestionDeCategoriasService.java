package connectforospa_gestiondecategorias.service;

import connectforospa_gestiondecategorias.model.Categorias;
import connectforospa_gestiondecategorias.repository.GestionDeCategoriasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GestionDeCategoriasService {

    private final GestionDeCategoriasRepository repo;

    public Categorias guardar(Categorias categoria) {
        return repo.save(categoria);
    }

    public List<Categorias> listarTodas() {
        return repo.findAll();
    }

    public Optional<Categorias> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
