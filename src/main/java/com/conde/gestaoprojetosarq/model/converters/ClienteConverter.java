package com.conde.gestaoprojetosarq.model.converters;

import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.dto.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {
    public ClienteDTO paraDTO(Cliente cliente){
        return ClienteDTO.builder()
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .telefoneContato(cliente.getTelefoneContato())
                .build();
    }


}
