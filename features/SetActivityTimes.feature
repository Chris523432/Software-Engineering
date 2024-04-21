Feature: Set activity times
  Description: Sets allocated time, start- and end week of an activity
  Actors: Employee

  Scenario: set allocated time
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the allocated time 72 hours is set on activity with id "1"
    Then the activity with id "1" has allocated time 72 hours

  Scenario: set allocated time to nonexisting activity
    Given employee "barc" is logged in
    #And there does not exist an activity with id 111
    When the allocated time 72 hours is set on activity with id "333"
    Then the error message "Activity does not exist" is given