package com.example.tacos.props;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.Value;

@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {
    
    @Min(value = 5, message = "PageSize is too small")
    @Max(value = 25, message = "PageSize is too big")
    private int pageSize;
}
