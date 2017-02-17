package com.midnight.reportsys.pojo;

import java.util.ArrayList;

/**
 * ClassName: Role <br/>
 * Function: 角色实体. <br/>
 * date: 2016年12月31日 下午1:52:27 <br/>
 * @author Midnight
 */
public class Role {
	//角色id
	private int id;
	//角色名称
	private String name;
	//角色对应的权限集合
	private ArrayList<Permission> permissions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
