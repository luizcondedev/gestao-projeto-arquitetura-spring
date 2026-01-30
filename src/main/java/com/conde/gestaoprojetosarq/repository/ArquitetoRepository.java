package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArquitetoRepository extends JpaRepository<Arquiteto, Long> {
     boolean existsByCpf(String cpf);
     boolean existsByEmail(String email);
     boolean existsByCau(String cau);
     Optional<Arquiteto> findByCpf(String cpf);

}
