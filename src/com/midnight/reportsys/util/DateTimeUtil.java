package com.midnight.reportsys.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	public static String getDateAndTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	public static String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	//获取本周的周末对应的日期
	public static String getWeekDate(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // 获取本周天的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime());
		
	}
	
	
}
