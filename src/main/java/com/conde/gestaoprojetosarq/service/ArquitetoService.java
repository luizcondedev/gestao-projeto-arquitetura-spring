package com.conde.gestaoprojetosarq.service;


import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ArquitetoDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.repository.ArquitetoRepository;
import com.conde.gestaoprojetosarq.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArquitetoService {
    @Autowired
    private ArquitetoRepository arquitetoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    public Arquiteto salvaArquiteto(Arquiteto arquiteto) {
        existeCau(arquiteto.getCau());
        existeCpf(arquiteto.getCpf());
        existeEmail(arquiteto.getEmail());
        return arquitetoRepository.save(arquiteto);
    }

    public void existeEmail(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public void existeCpf(String cpf) {
        try {
            boolean existe = verificaCpfExistente(cpf);
            if (existe) {
                throw new ConflictException("CPF já cadastrado");
            }
        } catch (ConflictException e) {
            throw new ConflictException("CPF já cadastrado" + e.getCause());
        }
    }

    public void existeCau(String cau) {
        try {
            boolean existe = verificaCauExistente(cau);
            if (existe) {
                throw new ConflictException("CAU já associado a outro arquiteto");
            }
        } catch (ConflictException e) {
            throw new ConflictException("CAU já associado a outro arquiteto" + e.getCause());
        }
    }

    public ArquitetoDTO buscarArquitetoPorCpf(String cpf) {
        Arquiteto arquiteto = arquitetoRepository.findByCpf(cpf)
                .orElseThrow(() -> new ConflictException("Arquiteto não encontrado com o CPF: " + cpf));

        ArquitetoDTO dto = new ArquitetoDTO();
        dto.setNome(arquiteto.getNome());
        dto.setEmail(arquiteto.getEmail());
        dto.setCpf(arquiteto.getCpf());
        dto.setCau(arquiteto.getCau());
        return dto;
    }

    public List<ProjetoDTO> buscarProjetosPorCpfDoArquiteto(String cpf) {
        List<Projeto> projetos = projetoRepository.findByArquitetoCpf(cpf);
        if (projetos.isEmpty()) {
            throw new ConflictException("Nenhum Projeto encontrado associado a esse arquiteto");
        }
        //Utlização de Stream para transformar a Lista Projetos em uma Lista de projetoDTO
        return projetos.stream()
                .map(projeto -> {
                    ProjetoDTO dto = new ProjetoDTO();
                    dto.setNomeProjeto(projeto.getNomeProjeto());
                    dto.setEnderecoProjeto(projeto.getEnderecoProjeto());
                    dto.setFaseProjeto(projeto.getFaseProjeto());
                    dto.setOrcamento(projeto.getOrcamento());
                    dto.setNomeArquiteto(projeto.getArquiteto().getNome());
                    dto.setNomeCliente(projeto.getCliente().getNome());
                    return dto;
                }).toList();
    }

    public List<ArquitetoDTO> listarTodos(){
        List<Arquiteto> arquitetos = arquitetoRepository.findAll();

        return arquitetos.stream()
                .map(arquiteto -> {
                    ArquitetoDTO dto = new ArquitetoDTO();
                    dto.setNome(arquiteto.getNome());
                    dto.setEmail(arquiteto.getEmail());
                    dto.setCpf(arquiteto.getCpf());
                    dto.setCau(arquiteto.getCau());
                    return dto;
                }).toList();
    }

    public ArquitetoDTO atualizarArquiteto(Long id, ArquitetoDTO dto){
        Arquiteto arquitetoExistente = arquitetoRepository.findById(id)
                .orElseThrow(() -> new ConflictException("Arquiteto não encontrado"));

        if(dto.getNome() != null){arquitetoExistente.setNome(dto.getNome());}

        arquitetoExistente.setNome(dto.getNome());
        arquitetoExistente.setEmail(dto.getEmail());
        arquitetoExistente.setCau(dto.getCau());
        arquitetoExistente.setCpf(dto.getCpf());

        arquitetoRepository.save(arquitetoExistente);
        return converterParaDto(arquitetoExistente);
    }

    public boolean verificaEmailExistente(String email) {
        return arquitetoRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf) {
        return arquitetoRepository.existsByEmail(cpf);
    }

    public boolean verificaCauExistente(String cau) {
        return arquitetoRepository.existsByEmail(cau);
    }

    private ArquitetoDTO converterParaDto(Arquiteto arquiteto){
        ArquitetoDTO dto = new ArquitetoDTO();
        dto.setNome(arquiteto.getNome());
        dto.setEmail(arquiteto.getEmail());
        dto.setCau(arquiteto.getCau());
        dto.setCpf(arquiteto.getCpf());
        return dto;
    }
}
