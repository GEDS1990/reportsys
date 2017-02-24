package com.midnight.reportsys.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.midnight.reportsys.pojo.Report;

public interface ReportService {
	// 保存报表信息
	void saveReport(Report report) throws Exception;

	// 文件上传
	String saveFileupload(MultipartFile url, String type) throws Exception;

	// 获取报表列表
	List<Report> getReportList(int intPage, int number, String type, int userId) throws Exception;

	// 获取报表数量
	int getReportCount(String type, int userId) throws Exception;

	// 根据id获取report对象
	Report getReportById(String id) throws Exception;

	// 根据id删除报表
	void deleteReportById(String id) throws Exception;

	// 获取指定类型报表截止时间
	Date getReportDeadline(String type) throws Exception;

	// 统计日或周报表的数量
	int getDailyOrWeeklyCount(String type, String time) throws Exception;

	
}
