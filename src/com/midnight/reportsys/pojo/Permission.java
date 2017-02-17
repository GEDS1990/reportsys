package com.midnight.reportsys.pojo;

/**
 * ClassName: Permission <br/>
 * Function: 权限实体，包含所有菜单资源. <br/>
 * date: 2016年12月31日 下午1:54:56 <br/>
 * @author Midnight
 * @param <T>
 */
public class Permission {
	//菜单id
	private int id;
	//菜单名称
	private String name;
	//菜单图标
	private String iconCls;
	//菜单对应的url页面
	private String url;
	//菜单的父id
	private int pid;
	
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
	//重写hashCode与equals方法:只要name相同,就表示同一个菜单
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
	

