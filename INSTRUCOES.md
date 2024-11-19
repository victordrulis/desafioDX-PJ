# Instruções e observações da implementação da solução do teste

## Bibliotecas utilizadas
- Lombok: Evitar _boilerplates_

## Banco de dados
- Utilizado o MySQL, sendo executado em conteiner Docker (arquivo ./compose.yaml)
- A porta do serviço foi mapeada para utilizar a padrão deste SGBD no host.  
- Os dados de conexão completos com usuario e senha estão no arquivo 

## application.properties
- Adicionado as propriedades para inicialização do BD MySql.

## Testes de unidade e integração
- Adicionados testes de unidade para as classes _Service_ e _Controllers_
- Testes de regras de negócio também foram adicionadas

