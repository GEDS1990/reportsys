package com.midnight.reportsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.midnight.reportsys.mapper.NoticeMapper;
import com.midnight.reportsys.pojo.Notice;
import com.midnight.reportsys.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	// 添加系统公告
	public void addNotice(Notice notice) throws Exception {
		noticeMapper.addNotice(notice);
	}
	
	// 获取指定类型的公告信息
	public List<Notice> findNotice(String type)throws Exception{
			return noticeMapper.findNotice(type);
	}
}
