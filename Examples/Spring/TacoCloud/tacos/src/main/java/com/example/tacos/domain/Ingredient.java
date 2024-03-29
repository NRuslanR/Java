package com.example.tacos.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "ingredients")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 5137450564054759176L;
    
    public static enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE, UNKNOWN };

    @Id
    private final String id;

    private final String name;

    @Enumerated(EnumType.STRING)
    private final Type type;

}
