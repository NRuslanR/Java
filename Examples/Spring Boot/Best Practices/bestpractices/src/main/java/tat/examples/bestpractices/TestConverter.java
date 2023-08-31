package tat.examples.bestpractices;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestConverter implements Converter<Mappee, Mapped> {

    private final ModelMapper modelMapper;

    @Override
    @Nullable
    public Mapped convert(Mappee source) {
        
        return modelMapper.map(source, Mapped.class);
    }
    
}
