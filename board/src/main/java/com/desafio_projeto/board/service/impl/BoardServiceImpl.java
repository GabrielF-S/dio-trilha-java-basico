package com.desafio_projeto.board.service.impl;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.Card;
import com.desafio_projeto.board.model.entity.CollumnBoard;
import com.desafio_projeto.board.model.repository.BoardRepository;
import com.desafio_projeto.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    public void generateBoard(String boardName, List<CollumnBoard> collumns) {

        Board board=  new Board(boardName, collumns);
        repository.save(board);
    }

    public List<Board> getAll() {
        List<Board> boards = repository.findAll();
        return boards;
    }

    public boolean existBoard(Long boardId) {
        return repository.existsById(boardId);
    }

    public Board getBoard(Long boardId) {
        return repository.findById(boardId).get();
    }

    public void deleteBoard(Board board){
        repository.delete(board);
    }

    public void updateBoard(Board board) {
        repository.save(board);
    }

    @Override
    public CollumnBoard getCollumnCancel(Board board) {
      return  board.getCollumns().stream().filter(collumn -> collumn.getPosition() == 0).findFirst().get();
    }

    @Override
    public void moveCardToCancel(Long idToSearch, Long boardId) {

        Board  board = repository.findById(boardId).get();
        Card cardToMove = findCardById(idToSearch, board);
        removeCardToCollumn(board, cardToMove);
        moveCardToCollumn(board, 0, cardToMove);
        repository.save(board);
    }

    private static void removeCardToCollumn(Board board, Card cardToMove) {
        board.getCollumns().stream().forEach(collmns -> {
            if (collmns.getCards().contains(cardToMove)) {
                collmns.getCards().remove(cardToMove);
            }
        });
    }

    @Override
    public CollumnBoard getCollumns(Board boardToShow, Long collumnId) {
       return boardToShow.getCollumns().stream().filter(collumn -> collumn.getId() == collumnId).findFirst().get();
    }

    @Override
    public void moveCard(Long boardID,Long idToSearch) {
        Board  board = repository.findById(boardID).get();
        Card card = findCardById(idToSearch, board);
        removeCardToCollumn(board,card);
        moveCardToCollumn(board, card.getCollumnBoard().getPosition() + 1, card);
        repository.save(board);
    }

    private static Card findCardById(Long idToSearch, Board board) {
        return board.getCollumns().stream()
                .flatMap(collumn -> collumn.getCards().stream())
                .filter(card -> card.getId() == idToSearch)
                .findAny().get();
    }

    private static void moveCardToCollumn(Board board, int x, Card cardToMove) {
        board.getCollumns().stream().filter(collumn -> collumn.getPosition() == x).findFirst().get().setCards(cardToMove);
    }
}
