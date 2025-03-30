package com.desafio_projeto.board.model.repository;

import com.desafio_projeto.board.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
