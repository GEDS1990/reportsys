package System;

import org.junit.Test;
import java.util.Calendar;

public class CalenderDay {

	@Test
	public void dayofweek() {
		Calendar calendar = Calendar.getInstance();
		
		
		System.out.println(Calendar.SATURDAY);
		System.out.println(Calendar.SUNDAY);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		
	}
}
