package com.conde.gestaoprojetosarq.model.converters;

import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import com.conde.gestaoprojetosarq.model.dto.ProjetoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteConverter {
    public ClienteDTO paraDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .telefoneContato(cliente.getTelefoneContato())
                .build();
    }

    public List<ClienteDTO> paraListaClienteDTO(List<Cliente> ClienteList){
        return ClienteList.stream().map(this::paraDTO).toList();
    }

    public List<ProjetoDTO> paraListaProjetoDTO(List<Projeto> ProjetoList){
        return ProjetoList.stream().map(this::paraProjetoDTO).toList();
    }

    public ProjetoDTO paraProjetoDTO(Projeto projeto){
        return ProjetoDTO.builder()
                .nomeProjeto(projeto.getNomeProjeto())
                .enderecoProjeto(projeto.getEnderecoProjeto())
                .faseProjeto(projeto.getFaseProjeto())
                .orcamento(projeto.getOrcamento())
                .nomeArquiteto(projeto.getArquiteto().getNome())
                .nomeCliente(projeto.getCliente().getNome())
                .build();
    }

}
