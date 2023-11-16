package com.apirest.curso.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstrutorRequest {

    private String nome;
    private String sobrenome;
    private String email;
    private String cursoProgramacao;
    private String hobby;

}
