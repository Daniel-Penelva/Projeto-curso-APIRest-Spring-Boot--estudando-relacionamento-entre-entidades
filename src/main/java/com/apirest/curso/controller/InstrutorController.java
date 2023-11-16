package com.apirest.curso.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.curso.entities.Instrutor;
import com.apirest.curso.entities.InstrutorDetalhes;
import com.apirest.curso.exception.ResourceNotFoundException;
import com.apirest.curso.repository.InstrutorDetalhesRepository;
import com.apirest.curso.repository.InstrutorRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/instrutor")
@AllArgsConstructor
public class InstrutorController {

    private InstrutorRepository instrutorRepository;

    private InstrutorDetalhesRepository instrutorDetalhesRepository;

    // localhost:8080/api/instrutor/all
    @GetMapping("/all")
    public List<Instrutor> listarInstrutores() {
        return instrutorRepository.findAll();
    }


    // localhost:8080/api/instrutor/search/{id}
    @GetMapping("/search/{id}")
    public ResponseEntity<Instrutor> buscarInstrutorPorId(@PathVariable Long id) {
        Instrutor instrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

        return ResponseEntity.ok().body(instrutor);
    }


    // Cria apenas o instrutor
    // localhost:8080/api/instrutor/create
    @PostMapping("/create")
    public Instrutor criarInstrutor(@Valid @RequestBody Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }

    // Cria o instrutor e o instrutor detalhe 
    // localhost:8080/api/instrutor/criar
    @PostMapping("/criar")
    public ResponseEntity<String> criarInstrutorAndInstrutorDetalhes(@RequestBody Instrutor instrutor) {
        
        // Aqui vai Certificar que o instrutorDetalhes não seja nulo
        InstrutorDetalhes instrutorDetalhes = instrutor.getInstrutorDetalhes();
        if (instrutorDetalhes != null) {
            // Vai certificar que o ID do instrutorDetalhes seja nulo para evitar problemas com a persistência
            instrutorDetalhes.setId(null);
            // Salva o instrutorDetalhes
            instrutorDetalhesRepository.save(instrutorDetalhes);
        }

        // Vai certificar que o ID do instrutor seja nulo para evitar problemas com a persistência
        instrutor.setId(null);
        
        // Salva o instrutor (incluindo a referência ao instrutorDetalhes)
        instrutorRepository.save(instrutor);

        return ResponseEntity.ok("Instrutor criado com sucesso!");
    }


    // localhost:8080/api/instrutor/replace/{id}
    @PutMapping("/replace/{id}")
    public ResponseEntity<Instrutor> atualizarInstrutor(@PathVariable Long id,
            @Valid @RequestBody Instrutor instrutor) {
        Instrutor buscarIdInstrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

        buscarIdInstrutor.setEmail(instrutor.getEmail());
        return ResponseEntity.ok(instrutorRepository.save(instrutor));
    }


    // Deleta apenas o Instrutor
    // localhost:8080/api/instrutor/delete/{id}
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletarInstrutor(@PathVariable Long id) {
        Instrutor buscarIdInstrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

        instrutorRepository.delete(buscarIdInstrutor);
        Map<String, Boolean> resposta = new HashMap<>();

        resposta.put("Instrutor Eliminado", Boolean.TRUE);
        return resposta;
    }

    // localhost:8080/api/instrutor/deletar/{id}
    @DeleteMapping("/deletar/{id}")
    public Map<String, Boolean> deletarInstrutorAndInstrutorDetalhes(@PathVariable Long id) {
        Instrutor instrutorExistente = instrutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

        // Captura os dados do instrutor 
        InstrutorDetalhes instrutorDetalhesExistente = instrutorExistente.getInstrutorDetalhes();
        
        // Verificar se existe instrutorDetalhes associado e deletar se existir
        if (instrutorDetalhesExistente != null) {
            // Deletar o instrutorDetalhes
            instrutorDetalhesRepository.delete(instrutorDetalhesExistente);
        }

        // Deletar o instrutor
        instrutorRepository.delete(instrutorExistente);

        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Instrutor e Detalhes Eliminados", Boolean.TRUE);
        return resposta;
    }

}
