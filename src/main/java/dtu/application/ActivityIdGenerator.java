package dtu.application;
//Bastian
public class ActivityIdGenerator implements IdGenerator {
    private static int previousActivityId = 0;

    @Override
    public String generateId() {
        previousActivityId++;
        return String.valueOf(previousActivityId);
    }

    @Override
    public void resetId() {
        previousActivityId = 0;
    }
}
