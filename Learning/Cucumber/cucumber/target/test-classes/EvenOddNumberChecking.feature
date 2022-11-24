Feature: Checks if number is even or add
    
    Scenario: Checks for Even number
        Given Create the even odd check instance
        When I will pass 2
        Then output should be "Even"

    Scenario: Checks for Odd number
        Given Create the even odd check instance
        When I will pass 3
        Then output should be "Odd"

