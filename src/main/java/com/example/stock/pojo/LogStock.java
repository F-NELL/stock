package com.example.stock.pojo;

import java.time.LocalDateTime;

import com.example.stock.pojo.state.Action;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "log")
public class LogStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date; // 01/02/2024

    @Enumerated(EnumType.STRING) // pour avoir le texte de la valeur 0 donc "AJOUT" en base
    private Action action;

    private Long produit_id;

    private Integer quantite;

    public LogStock(LocalDateTime date, Action action, Long produit_id, Integer quantite) {
        this.date = date;
        this.action = action;
        this.produit_id = produit_id;
        this.quantite = quantite;
    }

    public LogStock() {

    }
}
