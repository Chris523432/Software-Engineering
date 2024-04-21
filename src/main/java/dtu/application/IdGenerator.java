package dtu.application;

public class IdGenerator {
    private static int previousProjectId = 0;
    private static int previousActivityId = 0;
    private static int previousYear = 0;
    public String generateProjectId(int year) {
        if (previousYear < year) {
            resetProjectId();
        }
        previousYear = year;
        previousProjectId++;
        return year + String.format("%03d", previousProjectId);
    }
    public String generateActivityId() {
        previousActivityId++;
        return String.valueOf(previousActivityId);
    }
    public void resetProjectId() {
        previousProjectId = 0;
    }
    public void resetIds() {
        resetProjectId();
        previousActivityId = 0;
    }
}
