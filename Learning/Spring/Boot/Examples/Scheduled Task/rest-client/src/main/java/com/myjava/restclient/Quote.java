/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author ruslan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Value 
{
    private Long id;
    private String quote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
    
    @Override
    public String toString()
    {
        return String.format("{ \"id\": \"%s\", \"quote\": \"%s\" }", id, quote);
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        return String.format("{ \"type\": \"%s\", \"value\": \"%s\" }", type, value);
    }
}
