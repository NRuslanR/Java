package tat.examples.bestpractices;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mapped {
    
    private int first;
    private String second;
    private LocalDate subordinateCreationDate;
    private float subordinateVolume;
}
