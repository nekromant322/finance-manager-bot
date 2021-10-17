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

    @OneToOne
    private Category category;
}
