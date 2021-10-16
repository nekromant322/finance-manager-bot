package com.nekromant.finance.models;

import javax.persistence.*;

@Table(name = "transactions")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private Type type;

    @Column(name = "comment")
    private String commment;

    @Column(name = "sum")
    private Double sum;

    @OneToOne
    private Category category;

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
