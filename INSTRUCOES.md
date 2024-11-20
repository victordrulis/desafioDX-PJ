# Instruções e observações da implementação da solução do teste

### Bibliotecas utilizadas
- Lombok: Evitar _boilerplates_
- Commons-lang3: Usar metodos comuns para validar objetos

### Banco de dados
- Utilizado o MySQL, sendo executado em conteiner Docker (arquivo ./compose.yaml)
- A porta do serviço foi mapeada para utilizar a padrão deste SGBD no host.  
- Os dados de conexão completos com usuario e senha estão no arquivo 

### application.properties
- Adicionado as propriedades para inicialização do BD MySql.

### Testes de unidade e integração
- Adicionados testes de unidade para as classes _Service_ e _Controllers_
- Testes de regras de negócio também foram adicionadas

## Executando a aplicação

1. Navegar até a pasta raiz do projeto 
2. No terminal, executar
    ```
   $ docker-compose up compose.yaml
   ```
3. Iniciar a aplicação subindo o arquivo *war* no Tomcat
   Caso deseje executar pela IDE que já possua um servidor embutido, pode-se comentar/excluir a denpedencia "spring-boot-starter-tomcat"
- alternativamente, pode-se iniciar o app pela IDE executano o metodo main(...)
4. Iniciar a aplicação do front-end VUE
    ```
   $ npm run dev
   ```
5. Acessar as funcionalidades da aplicação a URL:
   ```
   http://localhost:8080/escalacao
   ```
6. A API com os endpoints conforme as regras do teste podem ser acessadas na raiz:
   ```
   /api
   ```