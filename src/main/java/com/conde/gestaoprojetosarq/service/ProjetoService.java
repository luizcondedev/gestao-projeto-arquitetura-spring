package com.conde.gestaoprojetosarq.service;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
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

    public Projeto criarProjeto(Projeto projeto){
        System.out.println("Projeto recebido: " + projeto);
        System.out.println("Arquiteto dentro do projeto: " + projeto.getArquiteto());

        if(projeto.getArquiteto() != null && projeto.getArquiteto().getId() != null){
            Arquiteto arquiteto = arquitetoRepository.findById(projeto.getArquiteto().getId())
                    .orElseThrow(() -> new RuntimeException("Arquiteto não encontrado pelo ID"));
            projeto.setArquiteto(arquiteto);
        }else{
            throw new RuntimeException("É obrigatório informar o ID do Arquiteto");
        }

        if(projeto.getCliente() != null && projeto.getCliente().getId() != null){
            Cliente cliente = clienteRepository.findById(projeto.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado pelo ID"));
            projeto.setCliente(cliente);
        }else{
            throw new RuntimeException("É obrigatório informar o ID do Cliente");
        }

        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodos(){
        return projetoRepository.findAll();
    }

}
