package com.desafio_projeto.board.service;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.CollumnBoard;

import java.util.List;

public interface BoardService {

     void generateBoard(String boardName, List<CollumnBoard> collumns);

     List<Board> getAll();

     boolean existBoard(Long boardId);

     Board getBoard(Long boardId);

     void deleteBoard(Board board);

     void updateBoard(Board board);

    CollumnBoard getCollumnCancel(Board board);

    void moveCardToCancel(Long idToSearch, Long boardId);

    CollumnBoard getCollumns(Board boardToShow, Long collumnId);

    void moveCard(Long boardId, Long idToSearch);
}
