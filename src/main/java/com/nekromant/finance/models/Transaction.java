package com.nekromant.finance.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Data
@Table(name = "transactions")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "comment")
    private String commment;

    @Column(name = "sum")
    private Double sum;

    @ManyToOne
    private Category category;

    public Transaction() {

    }

    public Transaction(UUID id, String commment, Double sum, Category category) {
        this.id = id;
        this.commment = commment;
        this.sum = sum;
        this.category = category;
    }
}
