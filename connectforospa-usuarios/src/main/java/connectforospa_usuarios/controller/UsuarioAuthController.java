package connectforospa_usuarios.controller;

import connectforospa_usuarios.model.UsuarioAuth;
import connectforospa_usuarios.service.UsuarioAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/usuarios")
@RequiredArgsConstructor
public class UsuarioAuthController {

    private final UsuarioAuthService service;

    @PostMapping
    public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioAuth auth) {
        service.guardar(auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<UsuarioAuth> listarUsuarios() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAuth> obtenerUsuario(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idUsuario}/estado")
    public ResponseEntity<UsuarioAuth> actualizarEstado(@PathVariable Long idUsuario, @RequestBody ActualizarEstado estado) {
        return service.actualizarEstado(idUsuario, estado.estado()) 
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private record ActualizarEstado(String estado) {} 
}
