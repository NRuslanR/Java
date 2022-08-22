package com.learning.hibernate.entities.converters;

import org.apache.commons.lang3.ArrayUtils;

import com.learning.hibernate.values.PersonName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonNameAttributeConverter
    implements AttributeConverter<PersonName, String> 
{

    @Override
    public String convertToDatabaseColumn(PersonName name) {
        
        return String.format("%s.%s", name.getLastName(), name.getFirstName());
    }

    @Override
    public PersonName convertToEntityAttribute(String fullName) {
        
        String[] nameParts = fullName.split("\\.");

        return nameParts.length == 2 ? 
                PersonName.of(nameParts[1], nameParts[0]) : PersonName.empty();
    }
    
}
