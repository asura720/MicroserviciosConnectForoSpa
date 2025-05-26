package connectforospa_publicacion.controller;

import connectforospa_publicacion.model.Publicacion;
import connectforospa_publicacion.service.PublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService service;

    @PostMapping("/crear/{idUsuario}")
    public Mono<ResponseEntity<Publicacion>> crearPublicacion(@PathVariable Long idUsuario, @RequestBody Publicacion publicacion) {
        publicacion.setIdUsuario(idUsuario);
        return service.guardarPublicacion(publicacion, idUsuario)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(new Publicacion())));
    }

    @PostMapping("/crear/admin/{idAdmin}")
    public Mono<ResponseEntity<Publicacion>> crearPublicacionAdmin(@PathVariable Long idAdmin, @RequestBody Publicacion publicacion) {
        return service.validarAdministrador(idAdmin)
                .flatMap(esAdmin -> {
                    if (!esAdmin) return Mono.just(ResponseEntity.status(403).body(new Publicacion()));
                    publicacion.setIdUsuario(idAdmin);
                    return service.guardarPublicacion(publicacion, idAdmin).map(ResponseEntity::ok);
                }).onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(new Publicacion())));
    }

    @PostMapping("/crear/coadmin/{idCoAdmin}")
    public Mono<ResponseEntity<Publicacion>> crearPublicacionCoAdmin(@PathVariable Long idCoAdmin, @RequestBody Publicacion publicacion) {
        return service.validarCoAdministrador(idCoAdmin)
                .flatMap(esCoAdmin -> {
                    if (!esCoAdmin) return Mono.just(ResponseEntity.status(403).body(new Publicacion()));
                    publicacion.setIdUsuario(idCoAdmin);
                    return service.guardarPublicacion(publicacion, idCoAdmin).map(ResponseEntity::ok);
                }).onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(new Publicacion())));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Publicacion>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(service.obtenerPublicacionesPorUsuario(idUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPorId(@PathVariable Long id) {
        Publicacion publicacion = service.obtenerPorId(id);
        return publicacion != null ? ResponseEntity.ok(publicacion) : ResponseEntity.notFound().build();
    }
}
