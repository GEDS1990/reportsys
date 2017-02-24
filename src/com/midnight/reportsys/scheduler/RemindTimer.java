package com.midnight.reportsys.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.midnight.reportsys.mapper.NoticeMapper;
import com.midnight.reportsys.pojo.Notice;

public class RemindTimer {
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private NoticeMapper noticeMapper = null;
	private CreateNoticeTask noticeTask = new CreateNoticeTask();

	public RemindTimer(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	public void executeRemindTimer() {
		Calendar calendar = Calendar.getInstance();
		TimerTask task1 = new TimerTask() {
			public void run() {
				try {
					// remind.remindGo();
					Notice notice = noticeTask.execute_dailyReport();
					noticeMapper.addNotice(notice);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		Calendar calendar1 = Calendar.getInstance();
		int year1 = calendar1.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
		calendar1.set(year1, month1, day1, 14, 30, 00);

		Date date1 = calendar1.getTime();
		Timer timer1 = new Timer();
		
		//间隔一天
		int period1 = 24*60*60*1000;

		if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)
				|| Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			// 周六周天不用提交报表
		} else {
			// 周一到周五提交报表
			timer1.schedule(task1, date1, period1);
		}
		
		

		TimerTask task2 = new TimerTask() {
			public void run() {
				try {
					// remind.remindGo();
					Notice notice = noticeTask.execute_weeklyReport();
					noticeMapper.addNotice(notice);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		Calendar calendar2 = Calendar.getInstance();
		int year2 = calendar2.get(Calendar.YEAR);
		int month2 = calendar2.get(Calendar.MONTH);
		//int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
		calendar2.set(year2, month2, Calendar.FRIDAY, 10, 30, 18);

		Date date2 = calendar2.getTime();
		Timer timer2 = new Timer();

		//间隔一周
		int period2 = 7*24*60*60*1000;
		timer2.schedule(task2, date2, period2);

	}

	public void stop() {
		scheduler.shutdown();
	}
}
