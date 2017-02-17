package com.midnight.reportsys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midnight.reportsys.pojo.Report;

public interface ReportMapper {
	// 保存报表信息
	void saveReport(Report report) throws Exception;

	// 根据类型和用户id获取报表列表
	List<Report> getReportList(@Param("type") String type, @Param("userId") int userId) throws Exception;

	// 根据类型和用户id获取报表数量
	int getReportCount(@Param("type") String type, @Param("userId") int userId) throws Exception;

	// 根据类型获取报表数量
	List<Report> getAllReportList(String type) throws Exception;

	// 根据类型获取报表数量
	int getAllReportCount(String type) throws Exception;

	// 根据id获取report对象
	Report getReportById(String id) throws Exception;

	// 根据id删除报表
	void deleteReportById(String id) throws Exception;
	
	//统计日或周报表的数量 
	int getDailyOrWeeklyCount(@Param("type")String type,@Param("time") String time) throws Exception;
}
