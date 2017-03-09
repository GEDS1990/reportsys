package com.midnight.reportsys.dto;

import com.midnight.reportsys.pojo.Report;
/**
 * 报表传输对象 
 * @author midnight
 *
 */
public class ReportDTO extends Report{

	//在线预览
	private String preview;

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	
}
