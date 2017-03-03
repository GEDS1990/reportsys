package com.midnight.reportsys.dto;

/**
 * 小组报表分析 传输对象
 * @author midnight
 *
 */
public class GroupDTO {
	private int id;
	private String role;
	private String name;
	private String daily;
	private String weekly;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	@Override
	public String toString() {
		return "GroupDTO [id=" + id + ", role=" + role + ", name=" + name + ", daily=" + daily + ", weekly=" + weekly
				+ "]";
	}
	
	
}
