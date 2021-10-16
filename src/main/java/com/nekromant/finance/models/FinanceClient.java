package com.nekromant.finance.models;

import javax.persistence.*;
import java.util.List;

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

    public FinanceClient() {

    }

    public FinanceClient(Long chatId, List<String> userNames, List<Category> categories,List<Transaction>transactionsHistory) {
        this.chatId = chatId;
        this.userNames = userNames;
        this.categories = categories;
        this.transactionsHistory = transactionsHistory;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
