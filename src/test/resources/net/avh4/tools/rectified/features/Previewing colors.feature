Feature: Previewing colors

Scenario: Simplest project
    Given a new design
    When I set the background color of the main region
    Then I see the new background color in the preview