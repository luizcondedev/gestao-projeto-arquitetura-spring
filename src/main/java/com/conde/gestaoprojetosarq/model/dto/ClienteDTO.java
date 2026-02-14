package com.conde.gestaoprojetosarq.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteDTO {
    private String nome;
    private String email;
    private String cpf;
    private String telefoneContato;
}
