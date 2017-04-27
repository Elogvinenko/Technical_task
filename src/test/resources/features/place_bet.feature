Feature: Place a bet
    Background:
        Given I open main page
        And I navigate to Football events

    Scenario: Place a bet and check results
        When I select "Sevilla" vs "Celta Vigo" football match
            And I fill 0.05 in bets
        Then total stake is displayed
            And returns are correct
