package com.midnight.reportsys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.midnight.reportsys.dto.UserDTO;
import com.midnight.reportsys.mapper.UserMapper;
import com.midnight.reportsys.pojo.Role;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.UserService;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * ClassName: UserServiceImpl <br/>
 * Function: 处理与用户信息相关的业务逻辑. <br/>
 * date: 2016年12月31日 下午5:05:37 <br/>
 * 
 * @author Midnight
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 添加用户
	public void addUser(User user, String roleId) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);
		userMapper.addUser(user);// 添加用户信息到数据库
		user = getUserByAccount(user.getAccount());// 从数据库获取用户id
		String[] roleIds = roleId.split(",");
		for(String rid : roleIds){
			int _rid = Integer.parseInt(rid);
			addUserRole(user.getId(), _rid); // 给用户添加角色

		}
		
	}

	// 添加用户角色
	public void addUserRole(int userId, int roleId) throws Exception {
		userMapper.addUserRole(userId, roleId);
	}

	// 获取全部用户
	public List<User> findAllUser() throws Exception {

		// TODO Auto-generated method stub
		return null;
	}

	// 获取用户数量
	public int getUserCount() throws Exception {

		return userMapper.getUserCount();
	}

	// 根据用户id获取用户
	public User getUserById(int id) throws Exception {

		
		return userMapper.getUserById(id);
	}

	// 根据用户account获取用户
	public User getUserByAccount(String account) throws Exception {
		return userMapper.getUserByAccount(account);
	}

	// 根据用户id获取所有角色
	public ArrayList<Role> getRoleByUserId(int id) throws Exception {

		ArrayList<Role> roles = userMapper.getRoleByUserId(id);
		return roles;
	}

	// 根据帐号和密码获取用户
	public User getUserByAccountAndPass(User user) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);
		
		return userMapper.getUserByAccountAndPass(user);
	}

	// 获取全部用户
	public List<User> getUserList(int intPage, int number) throws Exception {

		PageHelper.startPage(intPage, number);
		return userMapper.findAllUser();
	}

	// 获取全部用户
	public List<User> getUserList() throws Exception{
		return userMapper.findAllUser();
	}

	//获取需要提交报表成员的数量 
	public int getReportMemberCount()throws Exception{
		return userMapper.getReportMemberCount();
	}
	
	//更改用户密码
	public void updatePassword(String pass,int id)throws Exception{
		String md5pass = DigestUtils.md5Hex(pass);
		userMapper.updatePassword(md5pass, id);
	}

	//删除用户
	public void deleteUserById(int id) {
		userMapper.deleteUser(id);
		
	}
	//获取成员角色的用户列表
	public List<User> findMemberUserList(){
		return userMapper.findMemberUserList();
	}
	
	//获取组长或者管理角色的用户列表 
	public	List<User> findLeaderUserList(){
		return userMapper.findLeaderUserList();
	}
	
	//需要提交报表的成员
	public List<UserDTO> findMemberUser(int intPage, int number){
		PageHelper.startPage(intPage, number);
		List<User> lUsers = userMapper.findMemberUser();
		List<UserDTO> uDtos = new ArrayList<>();
		for (int i = 0; i < lUsers.size(); ++i) {
			User user = lUsers.get(i);
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setAccount(user.getAccount());
			userDTO.setUsername(user.getUsername());
			
			userDTO.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + user.getId() + "\">日报表分析</a>");
			userDTO.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + user.getId() + "\">周报表分析</a>");
			uDtos.add(userDTO);
		}
		
		
		return uDtos;
	}
	
	//需要提交报表的成员的数量
	public int findMemberUserCount() throws Exception{
		
		return userMapper.getReportMemberCount();
		
	}
}
