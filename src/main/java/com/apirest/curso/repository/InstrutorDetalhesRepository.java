package com.apirest.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.curso.entities.InstrutorDetalhes;

public interface InstrutorDetalhesRepository extends JpaRepository<InstrutorDetalhes, Long>{
    
}
