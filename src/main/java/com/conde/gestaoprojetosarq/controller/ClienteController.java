package com.conde.gestaoprojetosarq.controller;

import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(201).body(clienteService.salvarCliente(cliente));
    }

    @GetMapping("/busca/{cpf}")
    public ResponseEntity<ClienteDTO> buscarClientesCpf(@PathVariable String cpf){
        return ResponseEntity.ok(clienteService.buscarClientePorCpf(cpf));
    }
}
