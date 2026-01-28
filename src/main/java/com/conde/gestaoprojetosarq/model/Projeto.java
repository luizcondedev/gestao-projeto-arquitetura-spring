package com.conde.gestaoprojetosarq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "arquiteto_id")
    private Arquiteto arquiteto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
