package com.desafio_projeto.board.ui;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.Card;
import com.desafio_projeto.board.model.entity.CollumnBoard;
import com.desafio_projeto.board.service.BoardService;
import com.desafio_projeto.board.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Scanner;

@AllArgsConstructor
@Scope("prototype")
@Component
public class BoardMenu {
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    private final Board board;


   private final BoardService service;


    private final CardService cardService;

    public void execute() {

            System.out.printf("Bem vindo ao board ' %s - %s', selecione a operação desejada\n", board.getNameBoard(), board.getId());
            var option = -1;
            while (option != 9) {
                System.out.println("1 - Criar um card");
                System.out.println("2 - Mover um card");
                System.out.println("3 - Bloquear um card");
                System.out.println("4 - Desbloquear um card");
                System.out.println("5 - Cancelar um card");
                System.out.println("6 - Ver board");
                System.out.println("7 - Ver coluna com cards");
                System.out.println("8 - Ver card");
                System.out.println("9 - Voltar para o menu anterior um card");
                System.out.println("10 - Sair");
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> createCard();
                    case 2 -> moveCardToNextColumn();
                    case 3 -> blockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando para o menu anterior");
                    case 10 -> System.exit(0);
                    default -> System.out.println("Opção inválida, informe uma opção do menu");
                }
            }

    }

    private void createCard() {
        String title = requestString("Informe o titulo do card");
        String description  = requestString("Informe a descrição do card");
        Card card =  cardService.saveCard(title,description, OffsetDateTime.now(), board.getCollumns().get(0));
        board.getCollumns().get(0).setCards(card);
        service.updateBoard(board);


    }

    private void moveCardToNextColumn() {
        //TODO
        Long idToSearch = requestNumber("Informe o Id do card que deseja mover para a prox coluna");
        service.moveCard(board.getId(), idToSearch);
        cardService.moveCard(board,idToSearch);

    }

    private void blockCard() {
        Long idToSearch = requestNumber("Informe o Id do card");
        String description = requestString("Informe o motivo do bloqueio");
        cardService.blockCard(idToSearch, description);


    }

    private void showBoard() {
        Board entity = service.getBoard(board.getId());
        System.out.println("Board: " + board.getNameBoard());
        entity.getCollumns().forEach( collumnBoard -> {
            System.out.println("Coluna: " + collumnBoard.getNameCollumn());
            collumnBoard.getCards().forEach(System.out::println);
        });

    }

    private void unblockCard() {
        Long idToSearch = requestNumber("Informe o Id do card");
        String description = requestString("Informe o motivo do desbloqueio");
        cardService.unblockCard(idToSearch, description);


    }

    private void cancelCard() {

        CollumnBoard collumnToMove = service.getCollumnCancel(board);
        Long idToSearch = requestNumber("Informe o Id do card");
        cardService.cancelCard(idToSearch,collumnToMove );
        service.moveCardToCancel(idToSearch, board.getId());

    }

    private void showColumn() {
        //ToDo
        Board boardToShow = service.getBoard(board.getId());
        boardToShow.getCollumns().forEach(collumn -> System.out.printf("%s - %s [%s]\n", collumn.getId(), collumn.getNameCollumn(), collumn.getType() ));
        Long collumnId = requestNumber("Escolha o Id da coluna");
        CollumnBoard collumnBoard = service.getCollumns(boardToShow, collumnId);
        System.out.printf("Coluna %s tipo %s\n", collumnBoard.getNameCollumn(), collumnBoard.getType());
        if (!collumnBoard.getCards().isEmpty()){
            collumnBoard.getCards().forEach(card -> System.out.printf("Card %s - %s\nDescrição: %s\n", card.getId(), card.getTitle(),card.getDescription()));
        }else {
            System.out.printf("Coluna '%s' esta vazia\n", collumnBoard.getNameCollumn());
        }
    }

    private void showCard() {
        Long idToSearch = requestNumber("Informe o Id do card");
        Card cardToShow = cardService.getCard(idToSearch);
        System.out.printf("Card: %s - %s\n", cardToShow.getTitle(), cardToShow.getId());
        System.out.printf("Descrição: %s\n", cardToShow.getDescription());
        System.out.println(cardToShow.isBlock() ?
                "Está bloqueado. Motivo: " + cardToShow.getBlockDescription() :
                "Não está bloqueado");
        System.out.printf("Já foi bloqueado %s vezes\n", cardToShow.getBlockHistory().size());
        System.out.printf("Está no momento na coluna' %s - %s'\n", cardToShow.getCollumnBoard().getNameCollumn(), cardToShow.getCollumnBoard().getId());
    }

    private String requestString(String message) {
        System.out.println(message);
        String str = scanner.next();
        return str;

    }

    private Long requestNumber(String message) {
        System.out.println(message);
        String option = scanner.next();
        return Long.parseLong(option);
    }


}
