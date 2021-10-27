package com.nekromant.finance.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ElementCollection
    private List<String> keywords;

    private Type type;

    public Category() {

    }

    public Category(Long id, String name, List<String> keywords, Type type) {
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.type = type;
    }
}
