package com.example.tacos.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@Entity
@Table(name = "tacos")
@RestResource(rel = "tacos", path = "tacos")
public class Taco implements Serializable {

    private static final long serialVersionUID = 5877377659834274594L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;
    
    @NotNull
    @Size(min = 5, message = "Taco name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
        name = "tacos_ingredients",
        joinColumns = 
        {
            @JoinColumn(name = "taco_id", referencedColumnName = "id"),
        },
        inverseJoinColumns = 
        {
            @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
        }
    )
    @Size(min = 1, message = "Ingredient list must contain at least one ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt()
    {
        this.createdAt = new Date();
    }
}
