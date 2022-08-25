package com.learning.hibernate.entities;

import com.learning.hibernate.values.PersonName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString(callSuper = true)
@Table(name = "employees")
@RequiredArgsConstructor()
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person {
    
    public Employee(PersonName name, Address address, Workstation workstation)
    {
        super(name, address);
        this.workstation = workstation;
    }
    
    @NonNull
    @OneToOne(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH
        }
    )
    @JoinTable(
        name = "employee_workstation",
        joinColumns = {
            @JoinColumn(name = "employee_id", referencedColumnName = "person_id")
        },
            inverseJoinColumns = {
            @JoinColumn(name = "workstation_id", referencedColumnName = "id")
        }
    )
    private Workstation workstation;
    
}
