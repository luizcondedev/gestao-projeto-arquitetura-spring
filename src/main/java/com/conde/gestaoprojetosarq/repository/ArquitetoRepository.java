package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Arquiteto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquitetoRepository extends JpaRepository<Arquiteto, Long> {
    public boolean existsByCpf(String cpf);
    public boolean existsByEmail(String email);
    public boolean existsByCau(String cau);

}
