Feature: Show basic project information
  Description: Shows information about a project
  Actors: Employee

  Scenario: Show start week when project has start week registered
    Given employee "barc" is logged in
    And the project with name "project1" exists
    When the employee requests the start week of project with name "project1"
    Then the found start week, is the earliest start week of all activivites for "project1"
