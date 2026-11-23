package one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.lab_padroes_projeto_spring_desafio.gof.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
