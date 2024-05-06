package example.cucumber;

import dtu.application.Activity;
import dtu.application.Application;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//Bastian
public class SetActivityStatusSteps {
    private Application application;
    private ErrorMessageHolder errorMessageHolder;
    private Activity a;
    public SetActivityStatusSteps(Application application, ErrorMessageHolder errorMessageHolder) {
        this.application = application;
        this.errorMessageHolder = errorMessageHolder;
        application.resetAllIds();
    }
    @When("the activity with id {string} is marked as complete")
    public void theActivityWithIdIsMarkedAsComplete(String activityIdentifier) {
        try {
            a = application.getActivity(activityIdentifier);
            a.complete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the activity with id {string} is complete")
    public void theActivityWithIdIsComplete(String activityIdentifier) {
        assertTrue(a.isComplete());
    }
    @When("the activity with id {string} is marked as incomplete")
    public void theActivityWithIdIsMarkedAsIncomplete(String activityIdentifier) {
        try {
            a = application.getActivity(activityIdentifier);
            a.inComplete();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
    @Then("the activity with id {string} is incomplete")
    public void theActivityWithIdIsIncomplete(String activityIdentifier) {
        assertFalse(a.isComplete());
    }
}
