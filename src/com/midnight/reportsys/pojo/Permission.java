package com.midnight.reportsys.pojo;

/**
 * ClassName: Permission <br/>
 * Function: 权限实体，包含所有菜单资源. <br/>
 * date: 2016年12月31日 下午1:54:56 <br/>
 * 
 * @author Midnight
 * @param <T>
 */
public class Permission implements Comparable<Permission> {
	// 菜单id
	private int id;
	// 菜单名称
	private String name;
	// 菜单图标
	private String iconCls;
	// 菜单对应的url页面
	private String url;
	// 菜单的父id
	private int pid;
	// 排序
	private int sort;

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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public int compareTo(Permission o) {
		
		return this.getSort()>o.getSort()?1:(this.getSort()<o.getSort()?-1:0);
	}

	


}
