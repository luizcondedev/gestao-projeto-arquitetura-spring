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
        if(verificaEmailExistente(email)){
            throw new ConflictException("Email já cadastrado: " + email);
        }
    }

    public void existeCpf(String cpf) {
        if(verificaCpfExistente(cpf)){
            throw new ConflictException("Cpf já cadastrado: " + cpf);
        }
    }

    public void existeCau(String cau) {
        if(verificaCauExistente(cau)){
            throw new ConflictException("Cau já associado: " + cau);
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

        if(dto.getNome() != null){
            arquitetoExistente.setNome(dto.getNome());
        }

        if(dto.getEmail() != null && !dto.getEmail().equals(arquitetoExistente.getEmail())){
            if (verificaEmailExistente(dto.getEmail())){
                throw new ConflictException("Email já cadastrado em outro arquiteto");
            }
            arquitetoExistente.setEmail(dto.getEmail());
        }

        if(dto.getCau() != null && !dto.getCau().equals(arquitetoExistente.getCau())){
            if(verificaCauExistente(dto.getCau())){
                throw new ConflictException("CAU já cadastrado em outro arquiteto");
            }

            arquitetoExistente.setCau(dto.getCau());
        }

        if(dto.getCpf() != null && !dto.getCpf().equals(arquitetoExistente.getCpf())){
            if(verificaCpfExistente(dto.getCpf())){
                throw new ConflictException("CPF já cadastrado em outro arquiteto");
            }
        }

        arquitetoRepository.save(arquitetoExistente);
        return converterParaDto(arquitetoExistente);
    }

    public boolean verificaEmailExistente(String email) {
        return arquitetoRepository.existsByEmail(email);
    }

    public boolean verificaCpfExistente(String cpf) {
        return arquitetoRepository.existsByCpf(cpf);
    }

    public boolean verificaCauExistente(String cau) {
        return arquitetoRepository.existsByCau(cau);
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
