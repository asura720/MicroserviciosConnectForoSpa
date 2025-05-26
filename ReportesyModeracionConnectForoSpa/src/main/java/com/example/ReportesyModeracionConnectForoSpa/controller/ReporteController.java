package com.example.ReportesyModeracionConnectForoSpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.ReportesyModeracionConnectForoSpa.model.Reporte;
import com.example.ReportesyModeracionConnectForoSpa.service.ReporteService;

@RestController
@RequestMapping("api/reportes")
public class ReporteController {


    private final ReporteService service;

    public ReporteController(ReporteService service){
        this.service = service;
    }

    @GetMapping
    public List<Reporte> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Reporte getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public Reporte create(@RequestBody Reporte reporte){
        return service.save(reporte);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
