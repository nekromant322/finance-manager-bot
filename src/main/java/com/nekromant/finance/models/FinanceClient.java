package com.nekromant.finance.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.*;

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

    @Column
    private LocalDateTime subscriptionEndsLocalDate;
}
