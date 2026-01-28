package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Cliente;
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

    public boolean verificaEmailExistente(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf){
        return clienteRepository.existsByCpf(cpf);
    }
}
