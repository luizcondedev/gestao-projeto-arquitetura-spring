package com.conde.gestaoprojetosarq.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProjetoDTO {
    private String nomeProjeto;
    private String enderecoProjeto;
    private String faseProjeto;
    private BigDecimal orcamento;
    private String nomeArquiteto;
    private String nomeCliente;
}
