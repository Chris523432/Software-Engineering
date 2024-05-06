Guide for our Project Management application.

To run the tests associated with the program, the project must be opened with Maven. Furthermore when
opened with maven rightclick the Software-Engineering directory and find the tab "Run". Hover it and press "All Tests".
This if done correctly should run all features and tests associated with the program.

To run the program itself, go to the directories Software-Engineering/src/main/java/dtu/example.ui/View
Then run the View class, which will then open the GUI. From there the program can be run and tried out.

There is a login screen in the program, and there has been made 2 possible login initials. Either one works.
You login by typing either of the initials below:
Login: buba
Login: dani

Things to take into account when running the program:

When viewing a project the activities can be marked as complete or incomplete by clicking the activity
and pressing toggle completion.

When assigning the project leader, there won't be feedback from the frontend unless assigning an invalid employee.
But the assigned project leader will show when going back from the assign project leader scene.
There are 2 available employees that can be assigned in the program (buba and dani).

There is also no feedback at edit activity and assign employee, but if invalid input is given, it will show,
otherwise when the change is made through the press of a button, then you will have to go back like with
the assign project leader, where you'll be able to see the change.

In edit activity, only the chosen change with the corresponding input fields will be considered

When assigning a date there must be a week and a year associated with the date.


