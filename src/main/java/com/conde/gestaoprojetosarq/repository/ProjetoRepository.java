package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
