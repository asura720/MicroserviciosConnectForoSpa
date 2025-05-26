package connectforospa_usuarios.service;

import connectforospa_usuarios.model.UsuarioAuth;
import connectforospa_usuarios.repository.UsuarioAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioAuthService {

    private final UsuarioAuthRepository repository;

    public UsuarioAuth guardar(UsuarioAuth usuario) {
        return repository.save(usuario);
    }

    public List<UsuarioAuth> listarTodos() {
        return repository.findAll();
    }

    public Optional<UsuarioAuth> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<UsuarioAuth> actualizarEstado(Long idUsuario, String nuevoEstado) {
        return repository.findById(idUsuario).map(usuario -> {
            usuario.setEstado(nuevoEstado);
            return Optional.of(repository.save(usuario));
        }).orElse(Optional.empty());
    }
}
