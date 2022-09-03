package com.learning.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Table(name = "trees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 30)
public class Tree extends AbstractExampleEntity<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tree_id_gen")
    @TableGenerator(
        name = "tree_id_gen",
        allocationSize = 1,
        initialValue = 30,
        pkColumnName = "seq_id",
        valueColumnName = "tree_id",
        table = "tree_ids"
    )
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "branch_count")
    private int branchCount;

    @Override
    public Long getId() {
        
        return id;
        
    }
}
