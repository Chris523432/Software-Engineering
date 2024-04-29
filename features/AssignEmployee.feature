Feature: Assign employee to activity
  Description: Employee is assigned to an activity
  Actors: Employee

  Scenario: assign employees to activity
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    And the employee with initials "barc" is not assigned to activity with id "1"
    When the employee with initials "barc" is assigned to activity with id "1"
    Then the activity with id "1" has employee with initials "barc" assigned

  Scenario: assign duplicate employee to activity
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    And the employee with initials "barc" is assigned to activity with id "1"
    When the employee with initials "barc" is assigned to activity with id "1"
    Then the error message "Employee is already assigned to activity" is given.

  Scenario: assign non-existing employee to activity
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    And the employee with initials "wrng" is not registered in the system
    When the employee with initials "wrng" is assigned to activity with id "1"
    Then the error message "Employee does not exist" is given