Feature: Set activity status
  Description: Sets activity status
  Actors: Employee

  Scenario: set activity status as complete
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the activity with id "1" is marked as complete
    Then the activity with id "1" is complete

  Scenario: set activity status as incomplete
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the activity with id "1" is marked as incomplete
    Then the activity with id "1" is incomplete

  Scenario: set activity status of activity that does not exist
    Given employee "barc" is logged in
    And there does not exist an activity with id "1"
    When the activity with id "1" is marked as incomplete
    Then the error message "Activity does not exist" is given
