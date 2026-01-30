package com.conde.gestaoprojetosarq.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDTO {
    private String nome;
    private String email;
    private String cpf;
    private String telefoneContato;
}
