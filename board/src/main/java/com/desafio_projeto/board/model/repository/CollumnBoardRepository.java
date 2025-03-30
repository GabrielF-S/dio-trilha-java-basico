package com.desafio_projeto.board.model.repository;

import com.desafio_projeto.board.model.entity.CollumnBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollumnBoardRepository extends JpaRepository<CollumnBoard, Long> {
}
