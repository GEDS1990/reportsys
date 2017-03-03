package System;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CalenderDay {

	@Test
	public void dayofweek() {
		Calendar calendar = Calendar.getInstance();

		if (Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)) {
			// 周六周天不用提交报表
			System.out.println("xiangqitian");
		} else {
			System.out.println(",,,,,,,,,,,");
		}

	}

	
}

class test implements Runnable{

	@Override
	public void run() {
		System.out.println("hhhhhhhhhh");
	}
	
}
