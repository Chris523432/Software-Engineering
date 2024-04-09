package dtu.application;

import java.util.Calendar;

public class Project {
    private String name;
    private String id;
    private static int previousId = 0;

    public Project(String name, String year) {
        this.name = name;
        previousId += 1;
        this.id = year + String.format("%03d", previousId);
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

}
