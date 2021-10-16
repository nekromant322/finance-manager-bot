package com.nekromant.finance.models;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

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

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
