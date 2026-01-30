package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
            emailExiste(cliente.getEmail());
            cpfExiste(cliente.getCpf());
            return clienteRepository.save(cliente);
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já existente: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já existente" + e.getCause());
        }
    }

    public void cpfExiste(String cpf){
        try{
            boolean existe = verificaCpfExistente(cpf);
            if(existe){
                throw new ConflictException("Cpf já existente: " + cpf);
            }
        }catch (ConflictException e){
            throw new ConflictException("Cpf já existente" + e.getCause());
        }
    }

    public ClienteDTO buscarClientePorCpf(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ConflictException("Cliente não encontrado com esse cpf: " + cpf));

        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        dto.setTelefoneContato(cliente.getTelefoneContato());

        return dto;
    }

    public boolean verificaEmailExistente(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf){
        return clienteRepository.existsByCpf(cpf);
    }
}
