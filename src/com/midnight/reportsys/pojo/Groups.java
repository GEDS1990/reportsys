package com.midnight.reportsys.pojo;

/**
 * ClassName: Groups <br/>
 * Function: 分组实体. <br/>
 * date: 2016年12月31日 下午1:59:14 <br/>
 * @author Midnight
 */
public class Groups {
	//小组id
	private int id;
	//小组名称
	private String name;
	//小组组长
	private String leader;
	//小组成员
	private String member;
	//小组全部成员ids
	private String ids;
	
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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
