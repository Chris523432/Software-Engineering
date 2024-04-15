Feature: Create Activity
  Description: Activities are added to projects
  Actors: Employee

  Scenario: Make activity successfully
    Given project with name "project1" is in the system
    When an activity with name "activity1" is added to "project1"
    And the budgeted time is 72 hours
    Then the activity "activity1" with budgeted time 72 is in "project1"

  Scenario: An activity is added to project that is not in the system
    Given project with name "project1" is not in the system
    When an activity with name "activity1" is added to "project1"
    Then the error message "Project is not in the system" is given

  Scenario: Activity without name can not be added
    Given project with name "project1" is in the system
    When an activity with empty name (string is empty or only spaces) is added to "project1"
    Then the error message "Activity can not be added without a name" is given
    And activity with empty name is not in "project1"