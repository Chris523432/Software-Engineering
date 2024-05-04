Feature: Assign project leader
  Description: Assigns a project leader to a project
  Actors: Employee

  Scenario: Assign projectleader to project
    Given employee "barc" is logged in
    And the project with name "project1" exists
    And the employee with initials "baba" is registered in the system
    When the employee assigns "baba" as project leader in the project
    Then the employee "baba" is the project leader in the project

  Scenario: Assign non existing employee as project leader
    Given employee "barc" is logged in
    And the project with name "project1" exists
    And the employee with initials "baba" is not registered in the system
    When the employee assigns "baba" as project leader in the project
    Then the error message "Employee does not exist" is thrown