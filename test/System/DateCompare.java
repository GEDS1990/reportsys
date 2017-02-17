package System;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

public class DateCompare {
	@Test
	public void compare() throws ParseException, IOException {
		Properties pps = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/resource.properties");
		pps.load(in);

		String s = "2017-01-09 提交周报表的截止时间是： 23:00:00 ";
		String s1 = s.substring(0, 10);
		String s2 = s.substring(23,32);
		s =s1 + " " + s2;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(s);

		System.out.println(s);
		System.out.println(d);
		System.out.println(new Date());
	
		System.out.println(d.before(new Date()));

	}

	@Test
	public void getResource() throws IOException {
		Properties pps = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/resource.properties");
		pps.load(in);
		System.out.println(pps.getProperty("weekly.deadline"));
		// System.out.println(new
		// File("./config/resource.properties").getAbsolutePath());

	}

	@Test
	public void getWeek() throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // 获取本周天的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		System.out.println(df.format(cal.getTime()));
		
	}
}
