package com.conde.gestaoprojetosarq.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProjetoUpdateDTO {
    private String nomeProjeto;
    private String enderecoProjeto;
    private String faseProjeto;
    private BigDecimal orcamento;
    private Long arquitetoId;
}
