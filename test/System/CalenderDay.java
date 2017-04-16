package System;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.midnight.reportsys.util.DateTimeUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CalenderDay {

	@Test
	public void dayofweek() {
		Calendar calendar = Calendar.getInstance();

		if (Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)) {
			// 周六周天不用提交报表
			System.out.println("xingqitian");
		} else {
			System.out.println(",,,,,,,,,,,");
		}

	}

	@Test
	public void f() throws Exception {
		Properties pps = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/resource.properties");
		pps.load(in);

		String date = DateTimeUtil.getDate();
		String content = date +" "+ pps.getProperty("daily.deadline");
		System.out.println(content);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.parse(content));
	
	}

}
