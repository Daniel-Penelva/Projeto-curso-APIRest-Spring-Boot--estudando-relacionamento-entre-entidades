package com.apirest.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.curso.entities.Instrutor;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long>{
    
}
