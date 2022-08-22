package com.learning.hibernate.values;

import java.util.Objects;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonName {
    
    private static final PersonName emptyInstance = 
        new PersonName("", "");

    private final String firstName;
    private final String lastName;

    public static PersonName empty()
    {
        return emptyInstance;
    }

    public static PersonName of(String firstName, String lastName)
    {
        return new PersonName(firstName, lastName);
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
