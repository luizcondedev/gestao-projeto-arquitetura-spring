package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoUpdateDTO;
import com.conde.gestaoprojetosarq.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.status(201).body(projetoService.criarProjeto(projeto));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProjetoDTO>> listarTodos() {
        return ResponseEntity.ok(projetoService.listarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjetoDTO> atualizarProjeto(
            @PathVariable Long id,
            @RequestBody ProjetoUpdateDTO dto) {

        ProjetoDTO projetoAtualizado = projetoService.atualizarProjeto(id, dto);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletarProjeto (@PathVariable Long id){
        projetoService.deletarProjeto(id);
        String mensagem = "Projeto deletado com sucesso!!";

        return ResponseEntity.ok(mensagem);
    }
}
