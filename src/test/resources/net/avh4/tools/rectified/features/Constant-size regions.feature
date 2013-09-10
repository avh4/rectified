Feature: Constant-size regions

Scenario: top section in the main component
    Given a new design
    When I add a constant-size region to the top
    Then I see the new region