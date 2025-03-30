
# Projeto de board para gerenciamento de tarefas
Escreva um código que irá criar um board customizável para acompanhamento de tarefas


## Linguagens de Programação/Framework
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-000000?style=for-the-badge&logo=apachemaven&logoColor=blue)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Docker Pulls](https://img.shields.io/badge/docker-257bd6?style=for-the-badge&logo=docker&logoColor=white)
## Requisitos
    1 - O código deve iniciar disponibilizando um menu com as seguintes opções: Criar novo board, Selecionar board, Excluir boards, Sair;
    2 - O código deve salvar o board com suas informações no banco de dados MySQL;

## Regras dos boards
    1 - Um board deve ter um nome e ser composto por pelo menos 3 colunas ( coluna onde o card é colocado inicialmente, coluna para cards com tarefas concluídas e coluna para cards cancelados, a nomenclatura das colunas é de escolha livre);
    2 - As colunas tem seu respectivo nome, ordem que aparece no board e seu tipo (Inicial, cancelamento, final e pendente);
    3 - Cada board só pode ter 1 coluna do tipo inicial, cancelamento e final, colunas do tipo pendente podem ter quantas forem necessárias, obrigatoriamente a coluna inicial deve ser a primeira coluna do board, a final deve ser a penúltima e a de cancelamento deve ser a última
    4 - As colunas podem ter 0 ou N cards, cada card tem o seu título, descrição, data de criação e se está bloqueado;
    5 - Um card deve navegar nas colunas seguindo a ordem delas no board, sem pular nenhuma etapa, exceto pela coluna de cards cancelados que pode receber cards diretamente de qualquer coluna que não for a coluna final;
    6 - Se um card estiver marcado como bloqueado ele não pode ser movido até ser desbloqueado
    7 - Para bloquear um card deve-se informar o motivo de seu bloqueio e para desbloquea-lo deve-se também informar o motivo

## Menu de manipulação de board selecionado
    1 - O menu deve permitir mover o card para próxima coluna, cancelar um card, criar um card, bloquea-lo, desbloquea-lo e fechar board;


```mermaid
---
title: Board de Tarefas
---
classDiagram
class Board {
+Long id
+String nameBoard
+List~CollumnBoard~ collumns
}

    class CollumnBoard {
        +Long id
        +String nameCollumn
        +CollumnType type
        +List~Card~ cards
        +int position
    }

    class Card {
        +Long id
        +String title
        +String description
        +OffsetDateTime created
        +OffsetDateTime finisher
        +OffsetDateTime expectFinisher
        +CollumnBoard collumnBoard
        +boolean block
        +String blockDescription
        +List~String~ blockHistory
        +List~String~ desblockHistory
    }

    Board "1" --> "many" CollumnBoard
    CollumnBoard "1" --> "many" Card
    Card "many" --> "1" CollumnBoard
