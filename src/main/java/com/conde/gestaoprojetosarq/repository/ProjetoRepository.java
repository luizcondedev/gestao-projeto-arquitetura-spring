package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import com.conde.gestaoprojetosarq.model.Cliente;
import com.conde.gestaoprojetosarq.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
     long countByArquiteto(Arquiteto arquiteto);
     long countByCliente(Cliente cliente);
     List<Projeto> findByArquitetoCpf(String cpf);
}
