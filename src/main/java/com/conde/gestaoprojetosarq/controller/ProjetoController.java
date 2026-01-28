package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto){
        return ResponseEntity.status(201).body(projetoService.criarProjeto(projeto));
    }
}
