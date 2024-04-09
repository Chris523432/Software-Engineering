Feature: Login & Logout
  Description: Employee logs into the system and the employee logs out of the system.
  Actors: Employee

  Scenario: Employee login succeeds
    Given the employee with initials "barc" is registered in the system
    And the employee is not logged in
    Then the employee login succeeds
    And the employee is logged in

  Scenario: Employee login fails
    Given the employee with initials "wrng" is not registered in the system
    When the employee logs in
    Then the error message "Employee does not exist" is thrown

  Scenario: Employee logs out
    Given the employee with initials "barc" is registered in the system
    And the employee login succeeds
    When the employee logs out
    Then the employee is not logged in