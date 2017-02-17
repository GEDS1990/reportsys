package com.midnight.reportsys.pojo;

/**
 * ClassName: Notice <br/>
 * Function: 公告信息实体. <br/>
 * date: 2016年12月31日 下午2:13:26 <br/>
 * @author Midnight
 */
public class Notice {
	//公告id
	private String id;
	//公告内容
	private String content;
	//公告发布的日期
	private String createTime;
	//公告信息的类型
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String string) {
		this.createTime = string;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
}
