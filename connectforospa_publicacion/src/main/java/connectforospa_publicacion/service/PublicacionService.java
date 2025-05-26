package connectforospa_publicacion.service;

import connectforospa_publicacion.model.Publicacion;
import connectforospa_publicacion.repository.PublicacionRepository;
import connectforospa_publicacion.webclient.UsuarioClient;
import connectforospa_publicacion.webclient.CategoriaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicacionService {

    private final PublicacionRepository publicacionRepo;
    private final UsuarioClient usuarioClient;
    private final CategoriaClient categoriaClient;

    public Mono<Publicacion> guardarPublicacion(Publicacion publicacion, Long idUsuario) {
        return usuarioClient.obtenerUsuario(idUsuario)
                .filter(usuario -> usuario != null && usuario.getEstado() != null)
                .flatMap(usuario -> {
                    if ("ELIMINADO".equalsIgnoreCase(usuario.getEstado())) {
                        return Mono.error(new IllegalArgumentException("El usuario esta eliminado y no puede publicar."));
                    }
                    return categoriaClient.validarCategoria(publicacion.getIdCategoria())
                            .flatMap(existeCategoria -> {
                                if (!existeCategoria) {
                                    return Mono.error(new IllegalArgumentException("La categoría no existe."));
                                }
                                publicacion.setFechaPublicacion(LocalDateTime.now());
                                return Mono.just(publicacionRepo.save(publicacion));
                            });
                }).onErrorResume(error -> {
                    log.error("Error al guardar la publicación: {}", error.getMessage());
                    return Mono.empty();
                });
    }


    public Mono<Boolean> validarAdministrador(Long idAdmin) {
        return usuarioClient.obtenerUsuario(idAdmin)
                .filter(usuario -> usuario != null && usuario.getEmail() != null)
                .map(usuario -> usuario.getEmail().endsWith("@connectforo.cl"))
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> validarCoAdministrador(Long idCoAdmin) {
        return usuarioClient.obtenerUsuario(idCoAdmin)
                .filter(usuario -> usuario != null && usuario.getEmail() != null)
                .map(usuario -> usuario.getEmail().endsWith("@coadmin.connectforo.cl"))
                .defaultIfEmpty(false);
    }

    public List<Publicacion> obtenerPublicacionesPorUsuario(Long idUsuario) {
        return publicacionRepo.findByIdUsuario(idUsuario);
    }

    public Publicacion obtenerPorId(Long id) {
        return publicacionRepo.findById(id).orElse(null);
    }
}
