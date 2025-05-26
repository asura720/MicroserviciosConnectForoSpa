package com.example.SoporteTecnico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SoporteTecnico.model.SoporteTecnico;
import com.example.SoporteTecnico.service.SoporteTecnicoService;

@RestController
@RequestMapping("/microservicios/SoporteTecnico")
public class SoporteTecnicoController {

    private SoporteTecnicoService soporteTecnicoService;

    @Autowired
    public SoporteTecnicoController(SoporteTecnicoService soporteTecnicoService) {
        this.soporteTecnicoService = soporteTecnicoService;
    }


    @GetMapping
    public ResponseEntity<List<SoporteTecnico>> getAllInteracciones() {
        List<SoporteTecnico> lista2 = soporteTecnicoService.getAll();

        if(lista2.isEmpty()){
            //si esta vacia devuelvo un codigo not_content
            return ResponseEntity.noContent().build();
        }
        //si la lista tiene registros
        return ResponseEntity.ok(lista2);
    }


    @PostMapping
    public ResponseEntity<SoporteTecnico> save(@RequestBody SoporteTecnico soporteTecnico) {
        return ResponseEntity.ok(soporteTecnicoService.save(soporteTecnico));
    }
    
    @GetMapping("/{idSoporte}")
    public ResponseEntity<SoporteTecnico> obtenerPorId(@PathVariable Long idSoporte) {
        return ResponseEntity.ok(soporteTecnicoService.obtenerSoportePorId(idSoporte));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SoporteTecnico>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(soporteTecnicoService.obtenerSoportePorUsuario(idUsuario));
    }
}
