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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "keywords")
    @ElementCollection
    private List<String> keywords;

    @Column(name = "type")
    private Type type;
}
