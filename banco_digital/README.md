# Desafio de projeto: Criando Banco Digital

## Linguagens de Programação/Framework
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-000000?style=for-the-badge&logo=apachemaven&logoColor=blue)

### Desafio: 
Incrementar o código visto no curso com mais funções, acrescentar mais de nossos conhecimentos


## Diagrama UML

```mermaid
---
title: Banco Digital
---
classDiagram
    IConta <|--Conta
    IConta: +depositar(valor)
    IConta: +sacar(valor)
    IConta: +transferir(valor, contaDestino)
    IConta: +exibirInformacoes()
    IConta: +deastivarConta()

 

    class Conta {
       #int SEQUENCIAL
       #int AGENCIAPADRAO
       #int agencia
       #int numero
       #double saldo
       #bool isAtivado
       #Cliente cliente
    }

    
    contaCorrente --> Conta
    contaPoupanca --> Conta

    class Banco {
        -String nome
        -List<Cliente> cliente
    }
    Banco: +adicionarCliente()
    Banco: +consultarCliente()
    Banco: +consultarConta()

    ICliente <|--Cliente

    ICliente: criarConta()
    ICliente: listarContas()
    ICliente: selecionarConta()

    class Cliente {
        -String nome
        -String cpf
        -List<Contas> contas
    }
     Conta --o Cliente
     Banco --* Cliente