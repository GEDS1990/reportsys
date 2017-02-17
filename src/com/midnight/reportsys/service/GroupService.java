package com.midnight.reportsys.service;

import java.util.List;

import com.midnight.reportsys.pojo.Groups;

public interface GroupService {
	// 添加分组
	void addGroup(Groups group) throws Exception;

	// 查询全部分组
	List<Groups> findAllGroup(int intPage, int number) throws Exception;

	// 获取分组数量
	int getGroupCount() throws Exception;

	// 查询用户所在的小组
	List<Groups> findMyGroups(String id) throws Exception;
}
