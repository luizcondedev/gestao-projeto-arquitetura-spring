package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //Metodo para buscar o cliente pelo email
    public Cliente findByEmail(String email);

    //Metodo para verificar se ja existe um cliente com esse email
    public boolean existsByEmail(String email);
}
