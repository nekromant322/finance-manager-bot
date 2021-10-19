package com.nekromant.finance.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    private List<String> userNames;

    @OneToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column
    private List<Category> categories;

    @OneToMany
    @Column
    private List<Transaction> transactionsHistory;

    @Column
    private LocalDateTime subscriptionEndsLocalDate;

    public FinanceClient() {

    }

    public FinanceClient(Long id, List<String> userNames, List<Category> categories) {
        this.chatId = id;
        this.userNames = userNames;
        this.categories = categories;
    }

    public FinanceClient(Long chatId, List<String> userNames, List<Category> categories, List<Transaction> transactionsHistory) {
        this.chatId = chatId;
        this.userNames = userNames;
        this.categories = categories;
        this.transactionsHistory = transactionsHistory;
    }


}
