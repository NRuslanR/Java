package tat.examples.bestpractices;

import lombok.Data;

@Data
public class Mappee {
    
    private int first;
    private String second;

    private Subordinate subordinate;
}
