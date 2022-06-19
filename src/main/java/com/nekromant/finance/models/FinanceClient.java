package com.nekromant.finance.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column
    private List<Category> categories;

    @Column
    private LocalDateTime subscriptionEndsLocalDate;

    public FinanceClient() {
    }

    public FinanceClient(Long id, List<String> userNames, List<Category> categories) {
        this.chatId = id;
        this.userNames = userNames;
        this.categories = categories;
    }

}
