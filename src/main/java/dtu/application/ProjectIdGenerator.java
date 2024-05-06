package dtu.application;
//Bastian
public class ProjectIdGenerator implements IdGenerator {
    private static int previousProjectId = 0;
    private static int year = 0;
    @Override
    public String generateId() {
        previousProjectId++;
        return year + String.format("%03d", previousProjectId);
    }

    @Override
    public void resetId() {
        previousProjectId = 0;
    }

    public void setYear(int currentYear) {
        if (year < currentYear) {
            resetId();
        }
        year = currentYear;
    }
}
