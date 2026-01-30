package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ArquitetoDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.service.ArquitetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arquiteto")
public class ArquitetoController {
    @Autowired
    private ArquitetoService arquitetoService;

    @PostMapping
    public ResponseEntity<Arquiteto> salvaCliente(@RequestBody Arquiteto arquiteto){
       return ResponseEntity.status(201).body(arquitetoService.salvaArquiteto(arquiteto));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ArquitetoDTO>> listarTodos(){
        return ResponseEntity.ok(arquitetoService.listarTodos());
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<ArquitetoDTO> buscarPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(arquitetoService.buscarArquitetoPorCpf(cpf));
    }

    @GetMapping("/buscar/projetos/{cpf}")
    public ResponseEntity<List<ProjetoDTO>> buscarProjetosPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(arquitetoService.buscarProjetosPorCpfDoArquiteto(cpf));
    }
}
