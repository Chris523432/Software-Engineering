package example.cucumber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import dtu.application.DateServer;
import dtu.application.Application;

public class MockDateHolder {

    DateServer dateServer = mock(DateServer.class);

    public MockDateHolder(Application application) {
        GregorianCalendar calendar = new GregorianCalendar();
        setDate(calendar);
        application.setDateServer(dateServer);
    }
    public void setDate(Calendar calendar) {
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        when(this.dateServer.getDate()).thenReturn(c);
    }

    public int getYear() {
        return dateServer.getYear();
    }
}
