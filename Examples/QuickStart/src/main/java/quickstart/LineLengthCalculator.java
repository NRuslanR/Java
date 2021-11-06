package src.main.java.quickstart;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class LineLengthCalculator
{
    public List<Integer> calculate(String... lines)
    {
        return Stream.of(lines).map(line -> line.length()).collect(toList());
    }
}