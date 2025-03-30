package com.desafio_projeto.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameBoard;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CollumnBoard> collumns;

    public Board(String boardName, List<CollumnBoard> collumns) {
        this.nameBoard = boardName;
        this.collumns = collumns;
    }

    @Override
    public String toString() {
        return "Board{" +"\n" +
                "id: " + id + "\n" +
                "Name: '" + nameBoard + '\'' + "\n" +
                "collumns: " + collumns + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
