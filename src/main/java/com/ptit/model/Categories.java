package com.ptit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;
}