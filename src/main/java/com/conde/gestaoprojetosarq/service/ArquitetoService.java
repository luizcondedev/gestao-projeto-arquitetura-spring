package com.conde.gestaoprojetosarq.service;


import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.repository.ArquitetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquitetoService {
    @Autowired
    private ArquitetoRepository arquitetoRepository;

    public Arquiteto salvaArquiteto(Arquiteto arquiteto){
        existeCau(arquiteto.getCau());
        existeCpf(arquiteto.getCpf());
        existeEmail(arquiteto.getEmail());
        return arquitetoRepository.save(arquiteto);
    }

    public void existeEmail(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }
    public void existeCpf(String cpf){
        try {
            boolean existe = verificaCpfExistente(cpf);
            if(existe){
                throw new ConflictException("CPF já cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("CPF já cadastrado" + e.getCause());
        }
    }

    public void existeCau(String cau){
        try {
            boolean existe = verificaCauExistente(cau);
            if(existe){
                throw new ConflictException("CAU já associado a outro arquiteto");
            }
        } catch (ConflictException e) {
            throw new ConflictException("CAU já associado a outro arquiteto" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return arquitetoRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf){
        return arquitetoRepository.existsByEmail(cpf);
    }

    public boolean verificaCauExistente(String cau){
        return arquitetoRepository.existsByEmail(cau);
    }
}
