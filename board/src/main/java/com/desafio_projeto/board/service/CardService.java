package com.desafio_projeto.board.service;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.Card;
import com.desafio_projeto.board.model.entity.CollumnBoard;

import java.time.OffsetDateTime;

public interface CardService {
     Card saveCard(String title, String description, OffsetDateTime dataTime, CollumnBoard collumnBoard);

     void blockCard(Long idToSearch, String description);

    Card getCard(Long idToSearch);

    void unblockCard(Long idToSearch, String description);

    void cancelCard(Long idToSearch, CollumnBoard collumnToMove);


    void moveCard(Board board,Long idToSearch);
}
