package com.conde.gestaoprojetosarq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "arquitetos")
public class Arquiteto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "cpf", length = 14)
    private String cpf;
    @Column(name = "cau", length = 20)
    private String cau;

    @OneToMany(mappedBy = "arquiteto")
    List<Projeto> projetos;
}
