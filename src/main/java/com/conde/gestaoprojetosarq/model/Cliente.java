package com.conde.gestaoprojetosarq.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "cpf", length = 14)
    private String cpf;
    @Column(name = "telefone_contato", length = 20)
    private String telefoneContato;

    //mappedBy indicando qual atributo na classe Projeto pertence esse relacionamento
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    List<Projeto> projetos;
}
