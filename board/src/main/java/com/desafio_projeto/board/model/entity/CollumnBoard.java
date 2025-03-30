package com.desafio_projeto.board.model.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_board_collumn")
public class CollumnBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCollumn;
    private CollumnType type;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Card>cards;
    private int position;

    public CollumnBoard(String nameCollumn, CollumnType type, int position) {
        this.nameCollumn = nameCollumn;
        this.type = type;
        this.cards = new ArrayList<>();
        this.position = position;
    }

    public void setCards(Card card) {
        cards.add(card);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CollumnBoard that = (CollumnBoard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
