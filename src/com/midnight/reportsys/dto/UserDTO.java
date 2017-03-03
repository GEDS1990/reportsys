package com.midnight.reportsys.dto;

import com.midnight.reportsys.pojo.User;

/**
 * 用于查看用户报表的传输对象
 * @author midnight
 *
 */
public class UserDTO extends User{
	private String daily;
	private String weekly;
	public String getDaily() {
		return daily;
	}
	public void setDaily(String daily) {
		this.daily = daily;
	}
	public String getWeekly() {
		return weekly;
	}
	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}

	
}
