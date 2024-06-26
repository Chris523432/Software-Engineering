package example.junit;

import dtu.application.Application;
import dtu.application.DateServer;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//Bastian
public class MockDateHolder {

	DateServer dateServer;

	public MockDateHolder(Application application) {
		dateServer = mock(DateServer.class);
		GregorianCalendar calendar = new GregorianCalendar();
		setDate(calendar);
		application.setDateServer(dateServer);
	}

	public void setDate(Calendar calendar) {
		Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
		when(this.dateServer.getDate()).thenReturn(c);
	}

	public void advanceDateByYear() {
		Calendar currentDate = dateServer.getDate();
		Calendar nextDate = new GregorianCalendar();
		nextDate.setTime(currentDate.getTime());
		nextDate.add(Calendar.YEAR, 1);
		setDate(nextDate);
	}
	public void setYear2024() {
		Calendar c = new GregorianCalendar(2024, Calendar.JANUARY, 1);
		when(this.dateServer.getDate()).thenReturn(c);
	}
}
