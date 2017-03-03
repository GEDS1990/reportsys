package com.midnight.reportsys.pojo;

/**
 * ClassName: User <br/>
 * Function: 用户实体. <br/>
 * date: 2016年12月31日 下午1:47:05 <br/>
 * @author Midnight
 */
public class User {
	//用户id
	private int id;
	//用户账号
	private String account;
	//用户名称
	private String username;
	//用户密码
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
