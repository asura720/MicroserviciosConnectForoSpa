package com.example.InteraccionesSociales2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.InteraccionesSociales2.model.Interacciones;
import com.example.InteraccionesSociales2.service.InteraccionesService;

@RestController
@RequestMapping("/microservicios/interacciones")
public class InteraccionesController {

    private InteraccionesService interaccionesService;

    @Autowired
    public InteraccionesController(InteraccionesService interaccionesService) {
        this.interaccionesService = interaccionesService;
    }


    @GetMapping
    public ResponseEntity<List<Interacciones>> getAllInteracciones() {
        List<Interacciones> lista2 = interaccionesService.getAll();

        if(lista2.isEmpty()){
            //si esta vacia devuelvo un codigo not_content
            return ResponseEntity.noContent().build();
        }
        //si la lista tiene registros
        return ResponseEntity.ok(lista2);
    }


    @PostMapping
    public ResponseEntity<Interacciones> save(@RequestBody Interacciones interaccion) {
        return ResponseEntity.ok(interaccionesService.save(interaccion));
    }


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Interacciones>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(interaccionesService.obtenerInteraccionesPorUsuario(idUsuario));
    }

    @GetMapping("/notificacion/{idNotificacion}")
    public ResponseEntity<List<Interacciones>> obtenerInteraccionPorId(@PathVariable Long idInteraccion) {
        return ResponseEntity.ok(interaccionesService.obtenerInteraccionPorId(idInteraccion));
    }

    @DeleteMapping("/{idInteraccion}")
    public ResponseEntity<Void> deletePorId(@PathVariable Long idInteraccion) {
    interaccionesService.deletePorId(idInteraccion);
        return ResponseEntity.noContent().build();
    }

}
