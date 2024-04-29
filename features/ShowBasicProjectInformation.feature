Feature: Show basic project information
  Description: Shows information about a project
  Actors: Employee

  Scenario: Show start week when project has start week registered
    Given employee "barc" is logged in
    And the project with name "project1" exists
    And the activity with id "1" has the earliest start week 1 in year 2024
    When the employee requests the start week of project with name "project1"
    Then the found start week is week 1

  Scenario: Show start week when project does not have start week registered
    Given employee "barc" is logged in
    And the project with name "project1" exists
    When the employee requests the start week of project with name "project1"
    Then no date is given

  Scenario: Show start week when project does not exist
    Given employee "barc" is logged in
    And the project with name "project1" does not exist
    When the employee requests the start week of project with name "project1"
    Then the error message "Project is not in the system" is given

  Scenario: Show end week when project has end week registered
    Given employee "barc" is logged in
    And the project with name "project1" exists
    And the activity with id "1" has the latest end week 10 in year 2024
    When the employee requests the end week of project with name "project1"
    Then the found end week is week 10

  Scenario: Show end week when project does not have end week registered
    Given employee "barc" is logged in
    And the project with name "project1" exists
    When the employee requests the end week of project with name "project1"
    Then no date is given

  Scenario: Show end week when project does not exist
    Given employee "barc" is logged in
    And the project with name "project1" does not exist
    When the employee requests the end week of project with name "project1"
    Then the error message "Project is not in the system" is given

  Scenario: Show project status of completed project
    Given  employee "barc" is logged in
    And the project with name "project1" exists
    And "project1" is complete
    When the employee requests the status of "project1"
    Then the message "Project is complete" will be given

  Scenario: Show project status of not completed project
    Given employee "barc" is logged in
    And the project with name "project1" exists
    And "project1" is incomplete
    When the employee requests the status of "project1"
    Then the message "Project is incomplete" will be given

  Scenario: Show project status of project that does not exist
    Given employee "barc" is logged in
    And the project with name "project1" does not exists
    When the employee requests the status of "project1"
    Then the error message "Project is not in the system" is given