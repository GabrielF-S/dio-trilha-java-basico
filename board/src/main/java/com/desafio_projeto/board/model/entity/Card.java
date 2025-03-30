package com.desafio_projeto.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String title;
    private String description;
    private OffsetDateTime created;
    private OffsetDateTime finisher;
    private OffsetDateTime expectFinisher;
    @ManyToOne
    @JoinColumn(name = "collumn_board_id")
    private CollumnBoard collumnBoard;
    private boolean block;
    private String blockDescription;
    private List<String> blockHistory;
    private List<String> desblockHistory;

    public Card(String title, String description, OffsetDateTime now, CollumnBoard collumnBoard) {
        this.title = title;
        this.description = description;
        this.created = now;
        this.collumnBoard = collumnBoard;
        this.blockHistory = new ArrayList<>();
        this.desblockHistory = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Card{" +
                " title: '" + title + '\'' +
                "| id: " + id +
                "| description: '" + description + '\'' +
                "| block: " + block +
                "| blockDescription: '" + blockDescription + '\'' +
                "| created: " + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
