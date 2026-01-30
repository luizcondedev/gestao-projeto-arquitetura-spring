package com.conde.gestaoprojetosarq.repository;

import com.conde.gestaoprojetosarq.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //Metodo para buscar o cliente pelo email
     Cliente findByEmail(String email);

    //Metodo para verificar se ja existe um cliente com esse email
     boolean existsByEmail(String email);

     boolean existsByCpf(String cpf);

}
