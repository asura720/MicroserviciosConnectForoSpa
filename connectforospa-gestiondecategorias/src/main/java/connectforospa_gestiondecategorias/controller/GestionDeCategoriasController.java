package connectforospa_gestiondecategorias.controller;

import connectforospa_gestiondecategorias.model.Categorias;
import connectforospa_gestiondecategorias.service.GestionDeCategoriasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class GestionDeCategoriasController {

    private final GestionDeCategoriasService service;

    @PostMapping
    public ResponseEntity<Categorias> crearCategoria(@RequestBody Categorias categoria) {
        return ResponseEntity.ok(service.guardar(categoria));
    }

    @GetMapping
    public ResponseEntity<List<Categorias>> listarCategorias() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> obtenerCategoria(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
