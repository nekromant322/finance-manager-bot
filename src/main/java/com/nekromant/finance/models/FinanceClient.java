package com.nekromant.finance.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class FinanceClient {
    @Id
    @Column(name = "chatId")
    private Long chatId;
    @Column
    @ElementCollection
    List<String> userNames;

    @OneToMany
    @Column
    private List<Category> categories;

    @OneToMany
    @Column
    private List<Transaction> transactionsHistory;
}
