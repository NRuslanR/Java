package com.learning.hibernate.entities.converters;

import com.learning.hibernate.values.PersonName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonNameAttributeConverter
    implements AttributeConverter<PersonName, String> 
{

    @Override
    public String convertToDatabaseColumn(PersonName name) {
        
        return name.fullName();
    }

    @Override
    public PersonName convertToEntityAttribute(String fullName) {
        
        return PersonName.ofFullName(fullName);
    }
    
}
