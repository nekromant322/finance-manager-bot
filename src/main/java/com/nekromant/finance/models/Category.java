package com.nekromant.finance.models;

import lombok.Data;
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

    @Column(name = "name")
    private String name;

    @Column(name = "keywords")
    @ElementCollection
    private List<String> keywords;

    @Column(name = "type")
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
