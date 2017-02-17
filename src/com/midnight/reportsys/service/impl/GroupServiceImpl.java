package com.midnight.reportsys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.midnight.reportsys.mapper.GroupMapper;
import com.midnight.reportsys.mapper.UserMapper;
import com.midnight.reportsys.pojo.Groups;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.GroupService;


public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void addGroup(Groups group) throws Exception {
		String leader = group.getLeader();
		String ids = leader;
		//将id 变成 用户名
		int userId = Integer.parseInt(leader);
		
		User user = userMapper.getUserById(userId);
		leader = user.getUsername();
		group.setLeader(leader);
		
		//将id 变成 用户名
		String member = group.getMember();
		String[] members = member.split(",");
		StringBuffer sb = new StringBuffer();
		for(String m : members){
			ids = ids + "," + m;
			int _userId = Integer.parseInt(m);
			User _user = userMapper.getUserById(_userId);
			String _username = _user.getUsername();
			 sb.append(_username+",");
		}
		
		String s = sb.toString();
		member = s.substring(0,s.length()-1);
		group.setMember(member);
		group.setIds(ids);
		groupMapper.addGroup(group);
		
	}

	@Override
	public List<Groups> findAllGroup(int intPage, int number) throws Exception {
		PageHelper.startPage(intPage, number);
		return groupMapper.findAllGroup();
	}

	//获取分组数量
	public int getGroupCount() throws Exception{
		
		return groupMapper.getGroupCount();
	}
	//查询用户所在的小组
	public	List<Groups> findMyGroups(String id) throws Exception{
		
		return groupMapper.findMyGroups(id);
	}
}
