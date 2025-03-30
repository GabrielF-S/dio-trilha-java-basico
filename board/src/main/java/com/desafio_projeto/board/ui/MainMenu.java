package com.desafio_projeto.board.ui;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.CollumnBoard;
import com.desafio_projeto.board.model.entity.CollumnType;
import com.desafio_projeto.board.model.exceptions.BoardNotFoundExection;
import com.desafio_projeto.board.service.BoardService;
import com.desafio_projeto.board.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MainMenu {
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    @Autowired
    BoardService boardService;
    @Autowired
    CardService cardService;


    public void execute() {
        System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
        int option = -1;
        while (true) {
            System.out.println("1 - Criar um novo board");
            System.out.println("2 - Selecionar um board existente");
            System.out.println("3 - Excluir um board");
            System.out.println("4 - Exibir todos os borads");
            System.out.println("5 - Sair");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> exibirBoards();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida, informe uma opção do menu");
            }
        }

    }

    private void createBoard() {
        String boardName = requireString("Informe o nome para o Board: ");

        int qttCollums = requireNumber("O Board terá quantas colunas além das 3 colunas padrão?").intValue();

        List<CollumnBoard> collumns = new ArrayList<>();

        String collumnName = requireString("Informe o nome da coluna Inicial");
        CollumnBoard collumnInit = new CollumnBoard(collumnName, CollumnType.INITIAL, 1);
        collumns.add(collumnInit);
        for (int i = 1; i <= qttCollums; i++) {
            collumnName = requireString("Informe o nome da " + i + "° coluna adicional");
            CollumnBoard collumnPedding = new CollumnBoard(collumnName, CollumnType.PENDING, i + 1);
            collumns.add(collumnPedding);
        }

        collumnName = requireString("Informe o nome da coluna final");
        CollumnBoard collumnFinal = new CollumnBoard(collumnName, CollumnType.FINAL, qttCollums + 2);
        collumns.add(collumnFinal);

        collumnName = requireString("Informe o nome da coluna de Cancelados");
        CollumnBoard collumnCancel = new CollumnBoard(collumnName, CollumnType.CANCEL, 0);
        collumns.add(collumnCancel);

        boardService.generateBoard(boardName, collumns);

    }

    private void selectBoard() {
        Long boardId = requireNumber("Informe o Id do board");
        if (boardService.existBoard(boardId)) {
            Board board = boardService.getBoard(boardId);
            new BoardMenu(board, boardService, cardService).execute();
        } else {
            throw new BoardNotFoundExection("Board não localizado na base de dados");
        }
    }

    private void deleteBoard() {
        Long boardId = requireNumber("Informe o Id do board");
        if (boardService.existBoard(boardId)) {
            Board boardToDelete = boardService.getBoard(boardId);
            if (confirm("Tem certeza que deseja excluir o Board " + boardToDelete.getNameBoard() + "\n (y/n)")) {
                boardService.deleteBoard(boardToDelete);
                System.out.println("Board " + boardToDelete.getNameBoard() + " deletado");
            } else {
                System.out.println("Operação não realizada");
            }
        } else {
            throw new BoardNotFoundExection("Board não localizado na base de dados");
        }

    }

    private void exibirBoards() {
        List<Board> boards = boardService.getAll();
        boards.stream().forEach(System.out::println);
    }

    private Long requireNumber(String message) {

        System.out.println(message);
        String option = scanner.next();
        return Long.parseLong(option);
    }

    private String requireString(String message) {
        System.out.println(message);
        String str = scanner.next();
        return str;
    }

    private boolean confirm(String message) {
        String confirm = requireString(message);
        return confirm.equalsIgnoreCase("y");
    }
}