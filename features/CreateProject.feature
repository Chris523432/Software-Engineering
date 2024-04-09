Feature: Create Project
  Description: A project is created
  Actors: Employee

  Scenario: Create project successfully
    Given there is not a project with the name "Project1"
    And the year is 2024
    When a project with name "Project1" is created
    Then the project is in the system
    And the project with number "24001" is in the system

  Scenario: Project without name can not be created
    Given a project with empty name is created (string is empty or only spaces)
    Then the error message "Project can not be created without a name" is given
    And project with empty name is not in the system