package com.learning.hibernate.values;

import java.util.Objects;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class PersonName {
    
    private static final PersonName emptyInstance = 
        new PersonName("", "");
    
    private final String firstName;
    private final String lastName;

    @Transient
    private final String fullName;

    public PersonName(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", lastName, firstName);
    }

    public String fullName()
    {
        return fullName;
    }

    public static PersonName empty()
    {
        return emptyInstance;
    }

    public static PersonName of(String firstName, String lastName)
    {
        return new PersonName(firstName, lastName);
    }

    public static PersonName ofFullName(String fullName)
    {
        String[] nameParts = fullName.split(" ");

        return nameParts.length == 2 ? 
            PersonName.of(nameParts[1], nameParts[0]) : PersonName.empty();
    }

    @Override
    public boolean equals(Object other)
    {
        return !Objects.isNull(other) &&
                getClass().equals(other.getClass()) &&
                equals((PersonName) other);
    }
    
    private boolean equals(PersonName other)
    {
        return 
            getFirstName().equals(other.getFirstName()) &&
            getLastName().equals(other.getLastName());
    }
}
