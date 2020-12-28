/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.streams;

import java.util.Collection;
import java.util.stream.Stream;

/**
 *
 * @author ruslan
 */
public class GenericReducer<T> {
    
    public String reduce(Collection<T> items)
    {
        return reduce(items, ", ");
    }
    
    public String reduce(Collection<T> items, String delimiters)
    {
        return 
                Stream
                    .of(items)
                    .reduce(
                        "", 
                        (str, item) -> 
                            str == "" ? item.toString() : str + delimiters + item,
                        (partialStr1, partialStr2) -> partialStr1 + partialStr2
                    );
    }
    
}
