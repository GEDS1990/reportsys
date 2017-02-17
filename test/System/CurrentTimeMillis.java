package System;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class CurrentTimeMillis {
	@Test
	public void currentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}
}
