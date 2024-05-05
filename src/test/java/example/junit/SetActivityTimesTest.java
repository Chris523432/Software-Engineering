package example.junit;
import dtu.application.*;
import example.cucumber.ErrorMessageHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;


public class SetActivityTimesTest {
    private Activity activityTest;
    @Before
    public void setUp() {
         activityTest = new Activity("Test");
    }

    //setAllocatedTime Test
    @Test
    public void testInputDataSetA() throws OperationNotAllowedException {
        activityTest.setAllocatedTime(0);
        assertEquals(0, activityTest.getBudgetedHours());
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testInputDataSetB() throws OperationNotAllowedException{
        activityTest.setAllocatedTime(-100);
    }

    @Test
    public void testInputDataSetC() throws OperationNotAllowedException {
        activityTest.setAllocatedTime(100);
        assertEquals(100, activityTest.getBudgetedHours());
    }

    //setStartWeek Test
    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetA() throws OperationNotAllowedException {
        activityTest.setStartWeek(0, 0);
    }

    @Test
    public void testStartWeekInputDataSetB() throws OperationNotAllowedException {
        activityTest.setStartWeek(25, 2024);
        assertEquals(25, activityTest.getStartDate().get(Calendar.WEEK_OF_YEAR));
        assertEquals(2024,activityTest.getStartDate().get(Calendar.YEAR));
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetC() throws OperationNotAllowedException {
        activityTest.setStartWeek(-25, -2024);
    }
    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetD() throws OperationNotAllowedException {
        activityTest.setStartWeek(25, -2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetE() throws OperationNotAllowedException {
        activityTest.setStartWeek(-25, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetF() throws OperationNotAllowedException {
        activityTest.setStartWeek(55, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetG() throws OperationNotAllowedException {
        activityTest.setStartWeek(0, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testStartWeekInputDataSetH() throws OperationNotAllowedException {
        activityTest.setStartWeek(35, 0);
    }

    //setEndWeek Test
    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetA() throws OperationNotAllowedException {
        activityTest.setEndWeek(0, 0);
    }

    @Test
    public void testEndWeekInputDataSetB() throws OperationNotAllowedException {
        activityTest.setEndWeek(25, 2024);
        assertEquals(25, activityTest.getEndDate().get(Calendar.WEEK_OF_YEAR));
        assertEquals(2024,activityTest.getEndDate().get(Calendar.YEAR));
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetC() throws OperationNotAllowedException {
        activityTest.setEndWeek(-25, -2024);
    }
    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetD() throws OperationNotAllowedException {
        activityTest.setEndWeek(25, -2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetE() throws OperationNotAllowedException {
        activityTest.setEndWeek(-25, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetF() throws OperationNotAllowedException {
        activityTest.setEndWeek(55, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetG() throws OperationNotAllowedException {
        activityTest.setEndWeek(0, 2024);
    }

    @Test (expected = OperationNotAllowedException.class)
    public void testEndWeekInputDataSetH() throws OperationNotAllowedException {
        activityTest.setEndWeek(35, 0);
    }
}
