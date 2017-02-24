package com.midnight.reportsys.scheduler;

import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.midnight.reportsys.pojo.Notice;
import com.midnight.reportsys.service.NoticeService;
import com.midnight.reportsys.util.DateTimeUtil;

/**
 * 生成系统公告
 * @author Administrator
 *
 */
public class CreateNoticeTask {
	

	public Notice execute_dailyReport(){
		try {
			Properties pps = new Properties();
			InputStream in = this.getClass().getResourceAsStream("/resource.properties");
			pps.load(in);
			
			String date = DateTimeUtil.getDate();
			String content = date+" 提交日报表的截止时间是： "+pps.getProperty("daily.deadline");
			
			Notice notice = new Notice();
			notice.setId(UUID.randomUUID().toString());
			notice.setContent(content.trim());
			notice.setCreateTime(DateTimeUtil.getDateAndTime());
			notice.setType("daily");
			
			return notice;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public Notice execute_weeklyReport(){
		try {
			Properties pps = new Properties();
			InputStream in = this.getClass().getResourceAsStream("/resource.properties");
			pps.load(in);
			
			String date = DateTimeUtil.getWeekDate();
			String content = date+" 提交周报表的截止时间是： "+pps.getProperty("weekly.deadline");
			
			Notice notice = new Notice();
			notice.setId(UUID.randomUUID().toString());
			notice.setContent(content.trim());
			notice.setCreateTime(DateTimeUtil.getDateAndTime());
			notice.setType("weekly");

			return notice;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	
}
