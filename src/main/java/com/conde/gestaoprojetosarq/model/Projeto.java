package com.conde.gestaoprojetosarq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projetos")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_projeto", length = 100)
    private String nomeProjeto;
    @Column(name = "endereco_projeto")
    private String enderecoProjeto;
    @Column(name = "fase_projeto", length = 50)
    private String faseProjeto;
    @Column(name = "orcamento_projeto", precision = 19, scale = 2)
    private BigDecimal orcamento;

    @ManyToOne
    @JoinColumn(name = "arquiteto_id")
    @JsonIgnoreProperties("projetos")
    private Arquiteto arquiteto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties("projetos")
    private Cliente cliente;
}
