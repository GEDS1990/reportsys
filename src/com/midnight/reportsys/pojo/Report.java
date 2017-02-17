package com.midnight.reportsys.pojo;

/**
 * ClassName: Report <br/>
 * Function: 报表实体. <br/>
 * date: 2016年12月31日 下午2:08:43 <br/>
 * @author Midnight
 */
public class Report {
	//报表id
	private String id;
	//报表名称
	private String name;
	//报表类型
	private String type;
	//报表保存路径
	private String url;
	//上传日期
	private String createTime;
	//用户的id
	private int userId;
	//下载链接
	private String downloadUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
}
