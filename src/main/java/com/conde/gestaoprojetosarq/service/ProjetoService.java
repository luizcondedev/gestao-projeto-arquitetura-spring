package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.infrastructure.exceptions.ConflictException;
import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import com.conde.gestaoprojetosarq.repository.ArquitetoRepository;
import com.conde.gestaoprojetosarq.repository.ClienteRepository;
import com.conde.gestaoprojetosarq.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {
    @Autowired
    private ArquitetoRepository arquitetoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto criarProjeto(Projeto projeto) {
        System.out.println("Projeto recebido: " + projeto);
        System.out.println("Arquiteto dentro do projeto: " + projeto.getArquiteto());

        if (projeto.getArquiteto() != null && projeto.getArquiteto().getId() != null) {
            Arquiteto arquiteto = arquitetoRepository.findById(projeto.getArquiteto().getId())
                    .orElseThrow(() -> new RuntimeException("Arquiteto não encontrado pelo ID"));
            projeto.setArquiteto(arquiteto);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do Arquiteto");
        }

        if (projeto.getCliente() != null && projeto.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(projeto.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado pelo ID"));
            projeto.setCliente(cliente);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do Cliente");
        }

        validarLimiteArquiteto(projeto.getArquiteto());
        validarLimiteCliente(projeto.getCliente());

        return projetoRepository.save(projeto);
    }

    public void validarLimiteArquiteto(Arquiteto arquiteto) {
        if (contaProjetosArquitetos(arquiteto) >= 2) {
            throw new ConflictException("Arquiteto já atingiu o limite de 2 projetos");
        }
    }

    public void validarLimiteCliente(Cliente cliente) {
        if (contaProjetosCliente(cliente) >= 1) {
            throw new ConflictException("Cliente já atingiu o limite de 1 projeto");
        }
    }

    public long contaProjetosArquitetos(Arquiteto arquiteto) {
        return projetoRepository.countByArquiteto(arquiteto);
    }

    public long contaProjetosCliente(Cliente cliente) {
        return projetoRepository.countByCliente(cliente);
    }

    public List<ProjetoDTO> listarTodos() {
        List<Projeto> projetos = projetoRepository.findAll();
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

}
