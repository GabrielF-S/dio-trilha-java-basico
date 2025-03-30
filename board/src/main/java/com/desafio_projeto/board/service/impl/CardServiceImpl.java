package com.desafio_projeto.board.service.impl;

import com.desafio_projeto.board.model.entity.Board;
import com.desafio_projeto.board.model.entity.Card;
import com.desafio_projeto.board.model.entity.CollumnBoard;
import com.desafio_projeto.board.model.entity.CollumnType;
import com.desafio_projeto.board.model.exceptions.CardBlockException;
import com.desafio_projeto.board.model.exceptions.CardNotFoundException;
import com.desafio_projeto.board.model.exceptions.CollumnLimitException;
import com.desafio_projeto.board.model.repository.CardRepository;
import com.desafio_projeto.board.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository repository;



    public Card saveCard(String title, String description, OffsetDateTime dataTime, CollumnBoard collumnBoard){
        Card card = new Card(title, description, dataTime, collumnBoard);
        return repository.save(card);
    }

    public void blockCard(Long idToSearch, String description) {
        if (repository.existsById(idToSearch)){
            Card card = repository.findById(idToSearch).get();
            if (!card.isBlock()){
                card.setBlock(true);
                card.setBlockDescription(description);
                card.getBlockHistory().add("Block: "+ description);
                repository.save(card);
            }else {
                throw new CardBlockException("Card já esta bloqueado");
            }

        }
    }

    @Override
    public Card getCard(Long idToSearch) {
        if (repository.existsById(idToSearch)){
            Card card = repository.findById(idToSearch).get();
            return card;
        }else {
            throw new CardNotFoundException("Card não localizado na base");
        }

    }

    @Override
    public void unblockCard(Long idToSearch, String description) {
        if (repository.existsById(idToSearch)) {
            Card card = repository.findById(idToSearch).get();
            if (card.isBlock()) {
                card.setBlock(false);
                card.setBlockDescription(description);
                card.getDesblockHistory().add("Desblock: " + description);
                repository.save(card);
            } else {
                throw new CardBlockException("Card não esta bloqueado");
            }
        }
    }

    @Override
    public void cancelCard(Long idToSearch, CollumnBoard collumnToMove) {
        if (repository.existsById(idToSearch)){
            Card card = repository.findById(idToSearch).get();
            if (!card.isBlock()){
                card.setCollumnBoard(collumnToMove);
                repository.save(card);
            }else {
                throw new CardBlockException("Card Bloqueado para mover deve-se desbloquea-lo primeiramente");
            }

        }

    }

    @Override
    public void moveCard(Board board, Long idToSearch) {
        if (repository.existsById(idToSearch)){
            Card card = repository.findById(idToSearch).get();
            if (!card.isBlock()){
                if (card.getCollumnBoard().getType() != CollumnType.FINAL &&  card.getCollumnBoard().getType() != CollumnType.CANCEL){
                    CollumnBoard collumnBoard=  board.getCollumns().stream()
                            .filter(collumn -> collumn.getPosition() == card.getCollumnBoard().getPosition()+1)
                            .findAny().get();
                    card.setCollumnBoard(collumnBoard);
                    repository.save(card);
                }else {
                    throw  new CollumnLimitException("Não é possivel mover este card pois esta na coluna" + card.getCollumnBoard().getType());
                }


            }else {
                throw new CardBlockException("Card Bloqueado para mover deve-se desbloquea-lo primeiramente");
            }

        }
    }


}
