package com.example.HistorialUsuariosConnectForoSpa.controller;

import com.example.HistorialUsuariosConnectForoSpa.model.HistorialUsuario;
import com.example.HistorialUsuariosConnectForoSpa.service.HistorialUsuarioService;
import com.example.HistorialUsuariosConnectForoSpa.webclient.dto.HistorialUsuarioResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/historial")
public class HistorialUsuarioController {

    private final HistorialUsuarioService service;

    public HistorialUsuarioController(HistorialUsuarioService service){
        this.service = service;
    }

    @GetMapping
    public List<HistorialUsuarioResponse> getAll(){
        return service.getAll();
    }

    @PostMapping
    public HistorialUsuarioResponse create(@RequestBody HistorialUsuario historial){
        return service.save(historial);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }



}
