package com.midnight.reportsys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.midnight.reportsys.pojo.Groups;

public interface GroupMapper {
	//添加分组
	void addGroup(Groups group) throws Exception;
	
	//查询全部分组
	List<Groups> findAllGroup() throws Exception;
	
	//获取分组数量
	int getGroupCount() throws Exception;

	//查询用户所在的小组
	List<Groups> findMyGroups(@Param("id")String id) throws Exception;
}
