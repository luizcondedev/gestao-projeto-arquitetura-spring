package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.salvarCliente(cliente));
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<ClienteDTO> buscarClientesCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarClientePorCpf(cpf));
    }

    @GetMapping("/buscar/projetos/{cpf}")
    public ResponseEntity<List<ProjetoDTO>> buscarProjetosPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarProjetosPorCpfDoCliente(cpf));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        int projetosDeletados = clienteService.deletarCliente(id);

        String mensagem = "Cliente deletado com sucesso;";

        if(projetosDeletados > 0){
            mensagem += " " + projetosDeletados + " projeto(s) também foram deletados;";
        }

        return ResponseEntity.ok(mensagem);
    }
}
