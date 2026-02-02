package com.conde.gestaoprojetosarq.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class ProjetoUpdateDTO {
    private String nomeProjeto;
    private String enderecoProjeto;
    private String faseProjeto;
    private BigDecimal orcamento;
    private Long arquitetoId;
}
