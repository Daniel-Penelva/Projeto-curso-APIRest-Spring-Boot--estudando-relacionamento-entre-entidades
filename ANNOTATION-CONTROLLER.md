# Método HTTP no seu controlador `InstrutorController:`

## GET /api/instrutor/all:

- Descrição: Retorna uma lista de todos os instrutores.
- Método: GET
- Endpoint: /api/instrutor/all
- Ação: Retorna uma lista de todos os instrutores usando instrutorRepository.findAll().

```java
// localhost:8080/api/instrutor/all
@GetMapping("/all")
public List<Instrutor> listarInstrutores() {
    return instrutorRepository.findAll();
}
```

Este método é mapeado para a rota `/all` usando a anotação `@GetMapping`. Ele trata requisições HTTP GET para obter uma lista de todos os instrutores.

- **`@GetMapping("/all")`:**
  - Define que este método será chamado para requisições HTTP GET na rota `/all`.
  
- **`public List<Instrutor> listarInstrutores() { ... }`:**
  - O método é público e retorna uma lista de objetos `Instrutor`.
  - O tipo de retorno `List<Instrutor>` indica que este endpoint retornará uma lista de instrutores.

- **`return instrutorRepository.findAll();`:**
  - Utiliza o objeto `instrutorRepository` para chamar o método `findAll()`.
  - `findAll()` é um método fornecido por Spring Data JPA que retorna uma lista de todos os registros da entidade representada por `Instrutor`.
  - A lista resultante é então retornada como resposta à requisição.

**Passo a passo do funcionamento:**

1. **Requisição HTTP GET para `/all`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição GET para a rota `/all`.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@GetMapping("/all")`.

3. **Execução do Método:**
   - O método `listarInstrutores()` é executado quando a rota `/all` é acessada.

4. **Consulta ao Banco de Dados:**
   - `instrutorRepository.findAll()` chama o método `findAll()` no repositório `instrutorRepository`.
   - Essa chamada é traduzida para uma consulta SQL que busca todos os registros na tabela `Instrutor` no banco de dados.

5. **Retorno da Lista de Instrutores:**
   - A lista de instrutores resultante da consulta é retornada pelo método.

6. **Resposta ao Cliente:**
   - A lista de instrutores é enviada como resposta à requisição GET.
   - A resposta pode ser no formato JSON, se a aplicação estiver configurada para produzir JSON.

Esse endpoint é útil para recuperar todos os registros de instrutores disponíveis no banco de dados e é comumente usado para exibir uma lista de instrutores em uma interface de usuário.

## GET /api/instrutor/search/{id}:

- Descrição: Retorna um instrutor específico com base no ID.
- Método: GET
- Endpoint: /api/instrutor/search/{id}
- Ação: Retorna um instrutor com o ID especificado usando instrutorRepository.findById(id).

```java
// localhost:8080/api/instrutor/search/{id}
@GetMapping("/search/{id}")
public ResponseEntity<Instrutor> buscarInstrutorPorId(@PathVariable Long id) {
    Instrutor instrutor = instrutorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

    return ResponseEntity.ok().body(instrutor);
}
```

Este método é mapeado para a rota `/search/{id}` usando a anotação `@GetMapping`. Ele trata requisições HTTP GET para obter um instrutor específico com base no ID fornecido.

- **`@GetMapping("/search/{id}")`:**
  - Define que este método será chamado para requisições HTTP GET na rota `/search/{id}`.
  - O `{id}` é uma variável de caminho (path variable) que será mapeada para o parâmetro `id` do método.

- **`public ResponseEntity<Instrutor> buscarInstrutorPorId(@PathVariable Long id) { ... }`:**
  - O método é público e retorna um objeto do tipo `ResponseEntity<Instrutor>`.
  - `@PathVariable Long id`: Indica que o parâmetro `id` é extraído do caminho da URL.

- **`Instrutor instrutor = instrutorRepository.findById(id) ...`:**
  - Usa o objeto `instrutorRepository` para chamar o método `findById(id)`.
  - `findById(id)` é um método fornecido por Spring Data JPA que retorna um `Optional<Instrutor>`, representando o instrutor com o ID fornecido.
  - O método `.orElseThrow(...)` é usado para lançar uma exceção `ResourceNotFoundException` se o instrutor não for encontrado com o ID fornecido.

- **`return ResponseEntity.ok().body(instrutor);`:**
  - Se o instrutor for encontrado com sucesso, cria uma resposta HTTP 200 OK usando `ResponseEntity.ok()`.
  - O corpo (body) da resposta é definido como o instrutor encontrado.

**Passo a passo do funcionamento:**

1. **Requisição HTTP GET para `/search/{id}`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição GET para a rota `/search/{id}`, onde `{id}` é substituído por um valor numérico.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@GetMapping("/search/{id}")`.

3. **Extração do ID do Caminho:**
   - O valor numérico fornecido na URL é extraído e atribuído ao parâmetro `id` do método.

4. **Consulta ao Banco de Dados:**
   - `instrutorRepository.findById(id)` procura um instrutor no banco de dados com o ID fornecido.

5. **Lançamento de Exceção se Não Encontrado:**
   - Se nenhum instrutor for encontrado, uma exceção `ResourceNotFoundException` é lançada.

6. **Construção da Resposta HTTP:**
   - Se o instrutor for encontrado, uma resposta HTTP 200 OK é construída usando `ResponseEntity.ok()`.
   - O corpo da resposta é definido como o instrutor encontrado.

7. **Resposta ao Cliente:**
   - A resposta é enviada como resposta à requisição GET.
   - O cliente recebe os dados do instrutor, e a resposta pode ser no formato JSON, se a aplicação estiver configurada para produzir JSON.

Este endpoint é usado para recuperar informações detalhadas de um instrutor com base no ID fornecido.

## POST /api/instrutor/create:

- Descrição: Cria um novo instrutor.
- Método: POST
- Endpoint: /api/instrutor/create
- Ação: Cria um novo instrutor usando instrutorRepository.save(instrutor).

```java
// localhost:8080/api/instrutor/create
@PostMapping("/create")
public Instrutor criarInstrutor(@Valid @RequestBody Instrutor instrutor) {
    return instrutorRepository.save(instrutor);
}
```

Este método é mapeado para a rota `/create` usando a anotação `@PostMapping`. Ele trata requisições HTTP POST para criar um novo instrutor.

- **`@PostMapping("/create")`:**
  - Define que este método será chamado para requisições HTTP POST na rota `/create`.

- **`public Instrutor criarInstrutor(@Valid @RequestBody Instrutor instrutor) { ... }`:**
  - O método é público e retorna um objeto `Instrutor`.
  - `@Valid`: Indica que o objeto `instrutor` deve ser validado com base nas anotações de validação presentes em seu modelo.
  - `@RequestBody Instrutor instrutor`: Indica que o corpo da requisição HTTP (JSON, normalmente) deve ser convertido para um objeto `Instrutor`.

- **`return instrutorRepository.save(instrutor);`:**
  - Usa o objeto `instrutorRepository` para chamar o método `save(instrutor)`.
  - `save(instrutor)` é um método fornecido por Spring Data JPA que salva (insere) um novo instrutor no banco de dados.
  - O instrutor recém-criado, incluindo o ID gerado pelo banco de dados, é retornado como resultado da operação.

**Passo a passo do funcionamento:**

1. **Requisição HTTP POST para `/create`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição POST para a rota `/create`.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@PostMapping("/create")`.

3. **Validação do Corpo da Requisição:**
   - A anotação `@Valid` indica que o corpo da requisição (JSON) será validado com base nas anotações de validação presentes na classe `Instrutor`.

4. **Conversão do Corpo da Requisição para Objeto Instrutor:**
   - O corpo da requisição é convertido para um objeto `Instrutor` usando `@RequestBody`.
   - Esse objeto contém as informações do novo instrutor que está sendo criado.

5. **Persistência no Banco de Dados:**
   - `instrutorRepository.save(instrutor)` salva o novo instrutor no banco de dados.

6. **Retorno do Instrutor Criado:**
   - O instrutor recém-criado, incluindo o ID gerado pelo banco de dados, é retornado como resposta à requisição.

7. **Resposta ao Cliente:**
   - O cliente recebe os dados do instrutor que foi criado, e a resposta pode ser no formato JSON, se a aplicação estiver configurada para produzir JSON.

Este endpoint é usado para criar um novo instrutor no sistema quando um cliente envia uma requisição POST contendo os detalhes do instrutor a ser criado.

## POST /api/instrutor/criar:

- Descrição: Cria um novo instrutor e seus detalhes associados.
- Método: POST
- Endpoint: /api/instrutor/criar
- Ação: Cria um novo instrutor e, se existir, seus detalhes associados usando instrutorRepository.save(instrutor) e instrutorDetalhesRepository.save(instrutorDetalhes).

```java
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
```

Este método é mapeado para a rota `/criar` usando a anotação `@PostMapping`. Ele trata requisições HTTP POST para criar um novo instrutor e, opcionalmente, seus detalhes associados.

- **`@PostMapping("/criar")`:**
  - Define que este método será chamado para requisições HTTP POST na rota `/criar`.

- **`public ResponseEntity<String> criarInstrutorAndInstrutorDetalhes(@RequestBody Instrutor instrutor) { ... }`:**
  - O método é público e retorna um objeto do tipo `ResponseEntity<String>`.
  - `@RequestBody Instrutor instrutor`: Indica que o corpo da requisição HTTP (JSON, normalmente) deve ser convertido para um objeto `Instrutor`.

- **`InstrutorDetalhes instrutorDetalhes = instrutor.getInstrutorDetalhes();`:**
  - Obtém os detalhes do instrutor associados ao instrutor recebido.

- **`if (instrutorDetalhes != null) { ... }`:**
  - Verifica se há detalhes associados ao instrutor.

- **`instrutorDetalhes.setId(null);`:**
  - Certifica-se de que o ID dos detalhes do instrutor seja nulo para evitar problemas com a persistência (para permitir que o banco de dados atribua um novo ID automaticamente).

- **`instrutorDetalhesRepository.save(instrutorDetalhes);`:**
  - Salva os detalhes do instrutor no banco de dados, se existirem.

- **`instrutor.setId(null);`:**
  - Certifica-se de que o ID do instrutor seja nulo para evitar problemas com a persistência (para permitir que o banco de dados atribua um novo ID automaticamente).

- **`instrutorRepository.save(instrutor);`:**
  - Salva o instrutor no banco de dados, incluindo a referência aos detalhes do instrutor.

- **`return ResponseEntity.ok("Instrutor criado com sucesso!");`:**
  - Retorna uma resposta HTTP 200 OK com uma mensagem indicando que o instrutor foi criado com sucesso.

**Passo a passo do funcionamento:**

1. **Requisição HTTP POST para `/criar`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição POST para a rota `/criar`.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@PostMapping("/criar")`.

3. **Conversão do Corpo da Requisição para Objeto Instrutor:**
   - O corpo da requisição é convertido para um objeto `Instrutor` usando `@RequestBody`.
   - Esse objeto contém as informações do novo instrutor que está sendo criado, incluindo possíveis detalhes associados.

4. **Verificação e Salvamento dos Detalhes do Instrutor:**
   - Verifica se há detalhes associados ao instrutor.
   - Se existirem detalhes, é preciso certificar de que o ID seja nulo e, em seguida, salva-los no banco de dados usando `instrutorDetalhesRepository.save(instrutorDetalhes)`.

5. **Certificação do ID do Instrutor e Salvamento no Banco de Dados:**
   - É preciso certificar de que o ID do instrutor seja nulo para permitir que o banco de dados atribua um novo ID automaticamente.
   - Salva o instrutor no banco de dados, incluindo a referência aos detalhes do instrutor, usando `instrutorRepository.save(instrutor)`.

6. **Resposta ao Cliente:**
   - Retorna uma resposta HTTP 200 OK com uma mensagem indicando que o instrutor foi criado com sucesso.

Este endpoint é usado para criar um novo instrutor no sistema, e opcionalmente, seus detalhes associados quando um cliente envia uma requisição POST contendo os detalhes do instrutor a ser criado.

## PUT /api/instrutor/replace/{id}:

- Descrição: Atualiza um instrutor existente com base no ID.
- Método: PUT
- Endpoint: /api/instrutor/replace/{id}
- Ação: Atualiza um instrutor existente com base no ID usando instrutorRepository.save(instrutor).

```java
// localhost:8080/api/instrutor/replace/{id}
@PutMapping("/replace/{id}")
public ResponseEntity<Instrutor> atualizarInstrutor(@PathVariable Long id,
        @Valid @RequestBody Instrutor instrutor) {
    Instrutor buscarIdInstrutor = instrutorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " do instrutor não foi encontrado!"));

    buscarIdInstrutor.setEmail(instrutor.getEmail());
    return ResponseEntity.ok(instrutorRepository.save(instrutor));
}
```

Este método é mapeado para a rota `/replace/{id}` usando a anotação `@PutMapping`. Ele trata requisições HTTP PUT para atualizar as informações de um instrutor com base no ID fornecido.

- **`@PutMapping("/replace/{id}")`:**
  - Define que este método será chamado para requisições HTTP PUT na rota `/replace/{id}`.
  - O `{id}` é uma variável de caminho (path variable) que será mapeada para o parâmetro `id` do método.

- **`public ResponseEntity<Instrutor> atualizarInstrutor(@PathVariable Long id, @Valid @RequestBody Instrutor instrutor) { ... }`:**
  - O método é público e retorna um objeto do tipo `ResponseEntity<Instrutor>`.
  - `@PathVariable Long id`: Indica que o parâmetro `id` é extraído do caminho da URL.
  - `@Valid`: Indica que o objeto `instrutor` deve ser validado com base nas anotações de validação presentes no meu modelo.
  - `@RequestBody Instrutor instrutor`: Indica que o corpo da requisição HTTP (JSON, normalmente) deve ser convertido para um objeto `Instrutor`.

- **`Instrutor buscarIdInstrutor = instrutorRepository.findById(id) ...`:**
  - Usa o objeto `instrutorRepository` para chamar o método `findById(id)`.
  - `findById(id)` é um método fornecido por Spring Data JPA que retorna um `Optional<Instrutor>`, representando o instrutor com o ID fornecido.
  - O método `.orElseThrow(...)` é usado para lançar uma exceção `ResourceNotFoundException` se o instrutor não for encontrado com o ID fornecido.

- **`buscarIdInstrutor.setEmail(instrutor.getEmail());`:**
  - Atualiza o endereço de e-mail do instrutor com base nas informações fornecidas no corpo da requisição.

- **`return ResponseEntity.ok(instrutorRepository.save(instrutor));`:**
  - Retorna uma resposta HTTP 200 OK com o instrutor atualizado.
  - O instrutor atualizado é salvo no banco de dados usando `instrutorRepository.save(instrutor)`.

**Passo a passo do funcionamento:**

1. **Requisição HTTP PUT para `/replace/{id}`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição PUT para a rota `/replace/{id}`, onde `{id}` é substituído por um valor numérico.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@PutMapping("/replace/{id}")`.

3. **Extração do ID do Caminho:**
   - O valor numérico fornecido na URL é extraído e atribuído ao parâmetro `id` do método.

4. **Consulta ao Banco de Dados:**
   - `instrutorRepository.findById(id)` procura um instrutor no banco de dados com o ID fornecido.

5. **Lançamento de Exceção se Não Encontrado:**
   - Se nenhum instrutor for encontrado, uma exceção `ResourceNotFoundException` é lançada.

6. **Atualização das Informações do Instrutor:**
   - O método `buscarIdInstrutor.setEmail(instrutor.getEmail());` atualiza o endereço de e-mail do instrutor com base nas informações fornecidas no corpo da requisição.

7. **Persistência no Banco de Dados:**
   - O instrutor atualizado é salvo no banco de dados usando `instrutorRepository.save(instrutor)`.

8. **Retorno do Instrutor Atualizado:**
   - Retorna uma resposta HTTP 200 OK com o instrutor atualizado.

9. **Resposta ao Cliente:**
   - O cliente recebe os dados do instrutor que foi atualizado, e a resposta pode ser no formato JSON, se a aplicação estiver configurada para produzir JSON.

Este endpoint é usado para atualizar as informações de um instrutor com base no ID fornecido.

## DELETE /api/instrutor/delete/{id}:

- Descrição: Deleta um instrutor específico com base no ID.
- Método: DELETE
- Endpoint: /api/instrutor/delete/{id}
- Ação: Deleta um instrutor com o ID especificado usando instrutorRepository.delete(instrutor).

```java
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
```

Este método é mapeado para a rota `/delete/{id}` usando a anotação `@DeleteMapping`. Ele trata requisições HTTP DELETE para excluir um instrutor com base no ID fornecido.

- **`@DeleteMapping("/delete/{id}")`:**
  - Define que este método será chamado para requisições HTTP DELETE na rota `/delete/{id}`.
  - O `{id}` é uma variável de caminho (path variable) que será mapeada para o parâmetro `id` do método.

- **`public Map<String, Boolean> deletarInstrutor(@PathVariable Long id) { ... }`:**
  - O método é público e retorna um objeto do tipo `Map<String, Boolean>`.
  - `@PathVariable Long id`: Indica que o parâmetro `id` é extraído do caminho da URL.

- **`Instrutor buscarIdInstrutor = instrutorRepository.findById(id) ...`:**
  - Usa o objeto `instrutorRepository` para chamar o método `findById(id)`.
  - `findById(id)` é um método fornecido por Spring Data JPA que retorna um `Optional<Instrutor>`, representando o instrutor com o ID fornecido.
  - O método `.orElseThrow(...)` é usado para lançar uma exceção `ResourceNotFoundException` se o instrutor não for encontrado com o ID fornecido.

- **`instrutorRepository.delete(buscarIdInstrutor);`:**
  - Exclui o instrutor encontrado com base no ID usando `instrutorRepository.delete(buscarIdInstrutor)`.

- **`Map<String, Boolean> resposta = new HashMap<>();`:**
  - Cria um objeto `Map` para representar a resposta da operação.

- **`resposta.put("Instrutor Eliminado", Boolean.TRUE);`:**
  - Adiciona um par chave-valor ao mapa, indicando que o instrutor foi excluído com sucesso.

- **`return resposta;`:**
  - Retorna o mapa como resposta da operação.

**Passo a passo do funcionamento:**

1. **Requisição HTTP DELETE para `/delete/{id}`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição DELETE para a rota `/delete/{id}`, onde `{id}` é substituído por um valor numérico.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@DeleteMapping("/delete/{id}")`.

3. **Extração do ID do Caminho:**
   - O valor numérico fornecido na URL é extraído e atribuído ao parâmetro `id` do método.

4. **Consulta ao Banco de Dados:**
   - `instrutorRepository.findById(id)` procura um instrutor no banco de dados com o ID fornecido.

5. **Lançamento de Exceção se Não Encontrado:**
   - Se nenhum instrutor for encontrado, uma exceção `ResourceNotFoundException` é lançada.

6. **Exclusão do Instrutor:**
   - O instrutor encontrado é excluído do banco de dados usando `instrutorRepository.delete(buscarIdInstrutor)`.

7. **Criação da Resposta:**
   - Um mapa é criado para representar a resposta da operação.

8. **Indicação de Sucesso:**
   - Um par chave-valor é adicionado ao mapa, indicando que o instrutor foi excluído com sucesso.

9. **Resposta ao Cliente:**
   - O cliente recebe o mapa como resposta da operação, indicando o sucesso da exclusão.

Este endpoint é usado para excluir um instrutor do sistema com base no ID fornecido.

## DELETE /api/instrutor/deletar/{id}:

- Descrição: Deleta um instrutor e seus detalhes associados com base no ID.
- Método: DELETE
- Endpoint: /api/instrutor/deletar/{id}
- Ação: Deleta um instrutor e, se existir, seus detalhes associados usando instrutorRepository.delete- (instrutor) e instrutorDetalhesRepository.delete(instrutorDetalhes).

```java
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
```

Este método é mapeado para a rota `/deletar/{id}` usando a anotação `@DeleteMapping`. Ele trata requisições HTTP DELETE para excluir um instrutor e seus detalhes com base no ID fornecido.

- **`@DeleteMapping("/deletar/{id}")`:**
  - Define que este método será chamado para requisições HTTP DELETE na rota `/deletar/{id}`.
  - O `{id}` é uma variável de caminho (path variable) que será mapeada para o parâmetro `id` do método.

- **`public Map<String, Boolean> deletarInstrutorAndInstrutorDetalhes(@PathVariable Long id) { ... }`:**
  - O método é público e retorna um objeto do tipo `Map<String, Boolean>`.
  - `@PathVariable Long id`: Indica que o parâmetro `id` é extraído do caminho da URL.

- **`Instrutor instrutorExistente = instrutorRepository.findById(id) ...`:**
  - Usa o objeto `instrutorRepository` para chamar o método `findById(id)`.
  - `findById(id)` é um método fornecido por Spring Data JPA que retorna um `Optional<Instrutor>`, representando o instrutor com o ID fornecido.
  - O método `.orElseThrow(...)` é usado para lançar uma exceção `ResourceNotFoundException` se o instrutor não for encontrado com o ID fornecido.

- **`InstrutorDetalhes instrutorDetalhesExistente = instrutorExistente.getInstrutorDetalhes();`:**
  - Obtém os detalhes associados ao instrutor.

- **`if (instrutorDetalhesExistente != null) { ... }`:**
  - Verifica se existem detalhes associados ao instrutor.

- **`instrutorDetalhesRepository.delete(instrutorDetalhesExistente);`:**
  - Se existirem detalhes associados, eles são excluídos usando `instrutorDetalhesRepository.delete(instrutorDetalhesExistente)`.

- **`instrutorRepository.delete(instrutorExistente);`:**
  - O instrutor é excluído usando `instrutorRepository.delete(instrutorExistente)`.

- **`Map<String, Boolean> resposta = new HashMap<>();`:**
  - Cria um objeto `Map` para representar a resposta da operação.

- **`resposta.put("Instrutor e Detalhes Eliminados", Boolean.TRUE);`:**
  - Adiciona um par chave-valor ao mapa, indicando que o instrutor e seus detalhes foram excluídos com sucesso.

- **`return resposta;`:**
  - Retorna o mapa como resposta da operação.

**Passo a passo do funcionamento:**

1. **Requisição HTTP DELETE para `/deletar/{id}`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição DELETE para a rota `/deletar/{id}`, onde `{id}` é substituído por um valor numérico.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@DeleteMapping("/deletar/{id}")`.

3. **Extração do ID do Caminho:**
   - O valor numérico fornecido na URL é extraído e atribuído ao parâmetro `id` do método.

4. **Consulta ao Banco de Dados:**
   - `instrutorRepository.findById(id)` procura um instrutor no banco de dados com o ID fornecido.

5. **Lançamento de Exceção se Não Encontrado:**
   - Se nenhum instrutor for encontrado, uma exceção `ResourceNotFoundException` é lançada.

6. **Obtenção dos Detalhes do Instrutor:**
   - Os detalhes associados ao instrutor são obtidos.

7. **Verificação e Exclusão dos Detalhes:**
   - Se existirem detalhes associados, eles são excluídos do banco de dados usando `instrutorDetalhesRepository.delete(instrutorDetalhesExistente)`.

8. **Exclusão do Instrutor:**
   - O instrutor é excluído do banco de dados usando `instrutorRepository.delete(instrutorExistente)`.

9. **Criação da Resposta:**
   - Um mapa é criado para representar a resposta da operação.

10. **Indicação de Sucesso:**
    - Um par chave-valor é adicionado ao mapa, indicando que o instrutor e seus detalhes foram excluídos com sucesso.

11. **Resposta ao Cliente:**
    - O cliente recebe o mapa como resposta da operação, indicando o sucesso da exclusão do instrutor e seus detalhes.

Este endpoint é usado para excluir um instrutor e, se existirem, seus detalhes associados ao sistema com base no ID fornecido.

## POST /api/instrutor/criar:

- Descrição: Cria um novo instrutor e seus detalhes associados.
- Método: POST
- Endpoint: /api/instrutor/criar
- Ação: Cria um novo instrutor e, se existir, seus detalhes associados usando instrutorRepository.save(instrutor) e instrutorDetalhesRepository.save(instrutorDetalhes).

```java
// localhost:8080/api/instrutor/create-dto
@PostMapping("/create-dto")
public ResponseEntity<String> criarInstrutorDTO(@Valid @RequestBody InstrutorRequest instrutorRequest){

    // criar detalhes do instrutor
    InstrutorDetalhes instrutorDetalhes = new InstrutorDetalhes();
    instrutorDetalhes.setCursoProgramacao(instrutorRequest.getCursoProgramacao());
    instrutorDetalhes.setHobby(instrutorRequest.getHobby());

    // Salvar detalhes do instrutor
    instrutorDetalhesRepository.save(instrutorDetalhes);

    // Criar o instrutor
    Instrutor instrutor = new Instrutor();
    instrutor.setNome(instrutorRequest.getNome());
    instrutor.setSobrenome(instrutorRequest.getSobrenome());
    instrutor.setEmail(instrutorRequest.getEmail());
    instrutor.setInstrutorDetalhes(instrutorDetalhes);

    // Salvar o instrutor
    instrutorRepository.save(instrutor);

    return ResponseEntity.ok("Instrutor criado com sucesso!");
}
```

Este método é mapeado para a rota `/create-dto` usando a anotação `@PostMapping`. Ele trata requisições HTTP POST para criar um instrutor com base nos dados fornecidos no corpo da requisição (`@RequestBody InstrutorRequest instrutorRequest`).

- **`@PostMapping("/create-dto")`:**
  - Define que este método será chamado para requisições HTTP POST na rota `/create-dto`.

- **`public ResponseEntity<String> criarInstrutorDTO(@Valid @RequestBody InstrutorRequest instrutorRequest) { ... }`:**
  - O método é público e retorna um objeto do tipo `ResponseEntity<String>`.
  - `@Valid`: Indica que o corpo da requisição (`instrutorRequest`) deve ser validado de acordo com as regras de validação definidas nas anotações da classe `InstrutorRequest`.
  - `@RequestBody InstrutorRequest instrutorRequest`: Indica que os dados da requisição serão convertidos automaticamente para um objeto `InstrutorRequest`.

- **`InstrutorDetalhes instrutorDetalhes = new InstrutorDetalhes(); ...`:**
  - Cria uma nova instância de `InstrutorDetalhes`.

- **`instrutorDetalhes.setCursoProgramacao(instrutorRequest.getCursoProgramacao()); ...`:**
  - Configura os detalhes do instrutor com base nos dados fornecidos no corpo da requisição.

- **`instrutorDetalhesRepository.save(instrutorDetalhes);`:**
  - Salva os detalhes do instrutor no banco de dados usando `instrutorDetalhesRepository.save(instrutorDetalhes)`.

- **`Instrutor instrutor = new Instrutor(); ...`:**
  - Cria uma nova instância de `Instrutor`.

- **`instrutor.setNome(instrutorRequest.getNome()); ...`:**
  - Configura os dados do instrutor com base nos dados fornecidos no corpo da requisição.

- **`instrutor.setInstrutorDetalhes(instrutorDetalhes);`:**
  - Associa os detalhes do instrutor criados anteriormente ao instrutor.

- **`instrutorRepository.save(instrutor);`:**
  - Salva o instrutor no banco de dados usando `instrutorRepository.save(instrutor)`.

- **`return ResponseEntity.ok("Instrutor criado com sucesso!");`:**
  - Retorna uma resposta HTTP 200 OK com uma mensagem indicando que o instrutor foi criado com sucesso.

**Passo a passo do funcionamento:**

1. **Requisição HTTP POST para `/create-dto`:**
   - Um cliente (navegador, aplicativo, etc.) faz uma requisição POST para a rota `/create-dto`.

2. **Mapeamento do Controlador:**
   - O controlador recebe a requisição devido à anotação `@PostMapping("/create-dto")`.

3. **Validação dos Dados do Corpo da Requisição:**
   - Os dados fornecidos no corpo da requisição (`instrutorRequest`) são validados de acordo com as regras definidas nas anotações da classe `InstrutorRequest`.

4. **Criação dos Detalhes do Instrutor:**
   - Uma instância de `InstrutorDetalhes` é criada e configurada com base nos dados fornecidos no corpo da requisição.

5. **Salvamento dos Detalhes do Instrutor no Banco de Dados:**
   - Os detalhes do instrutor são salvos no banco de dados usando `instrutorDetalhesRepository.save(instrutorDetalhes)`.

6. **Criação do Instrutor:**
   - Uma instância de `Instrutor` é criada e configurada com base nos dados fornecidos no corpo da requisição.

7. **Associação dos Detalhes ao Instrutor:**
   - Os detalhes do instrutor criados anteriormente são associados ao instrutor.

8. **Salvamento do Instrutor no Banco de Dados:**
   - O instrutor é salvo no banco de dados usando `instrutorRepository.save(instrutor)`.

9. **Resposta ao Cliente:**
   - Uma resposta HTTP 200 OK é retornada com a mensagem "Instrutor criado com sucesso!".

Este endpoint é usado para criar um instrutor juntamente com seus detalhes no sistema com base nos dados fornecidos no corpo da requisição.

# Autor
## Feito por: `Daniel Penelva de Andrade`