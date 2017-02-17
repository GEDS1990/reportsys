package com.midnight.reportsys.mapper;

import java.util.List;

import com.midnight.reportsys.pojo.Notice;


public interface NoticeMapper {
	//添加系统公告
	void addNotice(Notice notice) throws Exception;
	
	// 获取指定类型的公告信息
	List<Notice> findNotice(String type)throws Exception;
}
