package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.service.ArquitetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arquiteto")
public class ArquitetoController {
    @Autowired
    private ArquitetoService arquitetoService;

    @PostMapping
    public ResponseEntity<Arquiteto> salvaCliente(@RequestBody Arquiteto arquiteto){
       return ResponseEntity.status(201).body(arquitetoService.salvaArquiteto(arquiteto));
    }
}
