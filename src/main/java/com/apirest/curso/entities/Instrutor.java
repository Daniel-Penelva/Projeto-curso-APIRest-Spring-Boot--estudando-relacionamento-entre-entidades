package com.apirest.curso.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instrutor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email;

    // Um instrutor vai pertence a um detalhe
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instrutor_detalhes_id")
    private InstrutorDetalhes instrutorDetalhes;

}
