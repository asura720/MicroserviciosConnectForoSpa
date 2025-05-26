package com.example.Notificaciones2.controller;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Notificaciones2.model.Notificaciones;
import com.example.Notificaciones2.service.NotificacionesService;

@RestController
@RequestMapping("/microservicios/notificaciones")
public class NotificacionesController {

    private NotificacionesService notificacionesService;


    @Autowired
    public NotificacionesController(NotificacionesService notificacionesService){
        this.notificacionesService = notificacionesService;
    }


    @GetMapping
    public ResponseEntity<List<Notificaciones>> getAllNotificaciones(){
        List<Notificaciones> lista2 = notificacionesService.getAll();

        if(lista2.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista2);
    }


    @PostMapping
    public ResponseEntity<Notificaciones> save(@RequestBody Notificaciones notificaciones){
        return ResponseEntity.ok(notificacionesService.save(notificaciones));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificaciones>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionesService.obtenerNotificacionPorUsuario(idUsuario));
    }

    @GetMapping("/notificacion/{idNotificacion}")
    public ResponseEntity<List<Notificaciones>> obtenerPorId(@PathVariable Long idNotificacion) {
        return ResponseEntity.ok(notificacionesService.obtenerNotificacionPorUsuario(idNotificacion));
    }
}
