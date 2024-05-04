Feature: Set activity times
  Description: Sets allocated time, start- and end week of an activity
  Actors: Employee

  Scenario: set allocated time
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the allocated time 72 hours is set on activity with id "1"
    Then the activity has allocated time 72 hours

  Scenario: set allocated time to nonexistent activity
    Given employee "barc" is logged in
    And there does not exist an activity with id "111"
    When the allocated time 72 hours is set on activity with id "111"
    Then the error message "Activity does not exist" is given

  Scenario: set invalid allocated time value
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the allocated time -10 hours is set on activity with id "1"
    Then the error message "Invalid value" is given

  Scenario: set start week successfully
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the start week is set to Week 1 and start year 2024 on activity with id "1"
    Then the activity with id "1" has start week Week 1 and start year 2024

  Scenario: start week set after end week.
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    And the activity id "1" has end week 5 and end year 2024
    When the start week is set to Week 6 and start year 2024 on activity with id "1"
    Then the error message "start week cannot be after end week" is given

  Scenario: set end week succesfully
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    When the end week is set to Week 3 and end year is set to 2024 on activity with id "1"
    Then the activity with id "1" has end week 3 and end year 2024

  Scenario: end week set before start week.
    Given employee "barc" is logged in
    And there exists an activity with id "1"
    And the activity has start week 5 and start year 2024
    When the end week is set to Week 3 and end year is set to 2024 on activity with id "1"
    Then the error message "End week cannot be before start week" is given